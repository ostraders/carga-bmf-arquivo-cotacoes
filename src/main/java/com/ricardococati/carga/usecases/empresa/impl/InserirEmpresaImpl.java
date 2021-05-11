package com.ricardococati.carga.usecases.empresa.impl;

import com.ricardococati.carga.adapters.repositories.ativo.AtivoBuscarDAO;
import com.ricardococati.carga.adapters.repositories.empresa.EmpresaInserirDAO;
import com.ricardococati.carga.entities.domains.ativo.Ativo;
import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import com.ricardococati.carga.usecases.empresa.InserirEmpresa;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InserirEmpresaImpl implements InserirEmpresa {

  private final EmpresaInserirDAO empresaInserirDAO;
  private final AtivoBuscarDAO ativoBuscarDAO;

  @Override
  public void salvar(final List<EmpresaDTO> empresaList) throws Exception {
    empresaList
        .stream()
        .filter(Objects::nonNull)
        .forEach(empresaDTO -> {
          insertEmpresa(empresaDTO);
        });
  }

  @SneakyThrows
  private void insertEmpresa(final EmpresaDTO empresaDTO) {
    try {
      List<Ativo> ativoList = ativoBuscarDAO.buscaAtivo(empresaDTO.getAtivo());
      if(ativoList.isEmpty()){
        log.info("COD_NEG não encontrado na base de ativos!");
      }
      empresaInserirDAO.incluirEmpresa(null);
    } catch (Exception e) {
      log.error("Erro ao inserir empresa: ", e.getMessage());
      throw new Exception("Erro ao inserir empresa: " + e.getMessage());
    }
  }

}
