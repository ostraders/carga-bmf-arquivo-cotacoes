package com.ricardococati.carga.usecases.empresa.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.carga.adapters.repositories.empresa.EmpresaBuscarDAO;
import com.ricardococati.carga.config.CustomPageImpl;
import com.ricardococati.carga.entities.domains.empresa.Empresa;
import com.ricardococati.carga.entities.domains.empresa.EmpresaResponse;
import com.ricardococati.carga.usecases.empresa.BuscarEmpresa;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

@Service
@RequiredArgsConstructor
public class BuscarEmpresaImpl implements BuscarEmpresa {

  private final EmpresaBuscarDAO empresaBuscarDAO;

  @Override
  public CustomPageImpl<EmpresaResponse> buscarPorNomeEmpresa(final String nomeEmpresa, final Pageable pageable) throws Exception {
    final List<EmpresaResponse> responseList = new ArrayList<>();
    if (nonNull(nomeEmpresa)) {
      responseList.addAll(getEmpresaResponseListByNomeEmpresa(nomeEmpresa, pageable));
    } else{
      responseList.addAll(getEmpresaResponseList(pageable));
    }
    return new CustomPageImpl<>(
        responseList,
        pageable,
        quantidadeEmpresas());
  }

  private List<EmpresaResponse> getEmpresaResponseListByNomeEmpresa(String nomeEmpresa, Pageable pageable) throws Exception {
    return empresaBuscarDAO
        .buscaEmpresasPorNome(nomeEmpresa, pageable.getPageSize(), (long) pageable.getOffset())
        .stream()
        .filter(Objects::nonNull)
        .map(this::convertEmpresaToResponse)
        .collect(Collectors.toList());
  }

  private List<EmpresaResponse> getEmpresaResponseList(final Pageable pageable) throws Exception {
    return empresaBuscarDAO
        .buscaEmpresasPorNome(pageable.getPageSize(), (long) pageable.getOffset())
        .stream()
        .filter(Objects::nonNull)
        .map(this::convertEmpresaToResponse)
        .collect(Collectors.toList());
  }

  private EmpresaResponse convertEmpresaToResponse(final Empresa empresa){
    return EmpresaResponse
        .builder()
        .idEmpresa(empresa.getIdEmpresa())
        .nomeEmpresa(empresa.getNomeEmpresa())
        .segmento(empresa.getSegmento())
        .setor(empresa.getSetor())
        .subSetor(empresa.getSubSetor())
        .build();
  }

  @Cacheable("quantidadeEmpresas")
  private Long quantidadeEmpresas(){
    return empresaBuscarDAO.quantidadeEmpresa();
  }
}
