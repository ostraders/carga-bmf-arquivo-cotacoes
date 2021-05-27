package com.ricardococati.carga.usecases.empresa.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.carga.adapters.repositories.ativo.AtivoBuscarDAO;
import com.ricardococati.carga.adapters.repositories.empresa.EmpresaBuscarDAO;
import com.ricardococati.carga.adapters.repositories.empresa.EmpresaInserirDAO;
import com.ricardococati.carga.adapters.repositories.empresaativo.EmpresaAtivoInsere;
import com.ricardococati.carga.adapters.repositories.gerasequencia.GeraSequenciaDAO;
import com.ricardococati.carga.entities.domains.ativo.Ativo;
import com.ricardococati.carga.entities.domains.empresa.Empresa;
import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import com.ricardococati.carga.entities.domains.empresaativo.EmpresaAtivo;
import com.ricardococati.carga.usecases.empresa.InserirEmpresa;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InserirEmpresaImpl implements InserirEmpresa {

  private static final String EMPRESA_SEQ = "EMPRESA_SEQ";
  private final EmpresaInserirDAO empresaInserirDAO;
  private final EmpresaBuscarDAO empresaBuscarDAO;
  private final EmpresaAtivoInsere empresaAtivoInsere;
  private final AtivoBuscarDAO ativoBuscarDAO;
  private final GeraSequenciaDAO genericDAO;

  @Override
  public void salvar(final List<EmpresaDTO> empresaList) {
    empresaList
        .stream()
        .filter(Objects::nonNull)
        .forEach(empresaDTO -> {
          insertEmpresa(empresaDTO);
        });
  }

  @Transactional
  @CacheEvict(value="quantidadeEmpresas",  allEntries = true)
  private void insertEmpresa(final EmpresaDTO empresaDTO) {
    try {
      Long idEmpresa = getIdEmpresaOrMaxIdEmpresa(empresaDTO);
      List<Ativo> ativoList = ativoBuscarDAO.buscaAtivo(empresaDTO.getAtivo());
      if(ativoList.isEmpty()){
        log.info("COD_NEG não encontrado na base de ativos!");
        throw new RuntimeException("Não foram encontrados ativos, empresa não será cadastrada!");
      }
      if (empresaInserirDAO.incluirEmpresa(convertDTOToEmpresa(empresaDTO, idEmpresa))) {
        ativoList.stream()
            .filter(Objects::nonNull)
            .forEach(
                ativo -> {
                  empresaAtivoInsere.insereAtivoEmpresa(
                      buildEmpresaAtivo(idEmpresa, ativo.getIdAtivo()));
                });
      }
    } catch (Exception e) {
      log.error("Erro ao inserir empresa: ", e.getMessage());
      throw new RuntimeException("Erro ao inserir empresa: " + e.getMessage());
    }
  }

  private Long getIdEmpresaOrMaxIdEmpresa(final EmpresaDTO empresaDTO) throws Exception {
    Long idEmpresa = getIdEmpresaSequence();
    Empresa empresa = empresaBuscarDAO.buscaEmpresaPorNome(empresaDTO.getNomeEmpresa());
    if(nonNull(empresa)){
      idEmpresa = empresa.getIdEmpresa();
    }
    return idEmpresa;
  }

  private Empresa convertDTOToEmpresa(final EmpresaDTO empresaDTO, final Long idEmpresa){
    return Empresa
        .builder()
        .idEmpresa(idEmpresa)
        .nomeEmpresa(empresaDTO.getNomeEmpresa().toUpperCase())
        .segmento(empresaDTO.getSegmento())
        .setor(empresaDTO.getSetor())
        .subSetor(empresaDTO.getSubSetor())
        .build();
  }

  private EmpresaAtivo buildEmpresaAtivo(final Long idEmpresa, final Long idAtivo){
    return EmpresaAtivo
        .builder()
        .idEmpresa(idEmpresa)
        .idAtivo(idAtivo)
        .build();
  }

  private Long getIdEmpresaSequence() {
    return genericDAO.getSequence(EMPRESA_SEQ).longValue();
  }

}
