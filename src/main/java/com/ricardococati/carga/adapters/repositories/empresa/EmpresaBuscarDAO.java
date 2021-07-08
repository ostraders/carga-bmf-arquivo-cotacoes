package com.ricardococati.carga.adapters.repositories.empresa;

import com.ricardococati.carga.entities.domains.empresa.Empresa;
import java.util.List;

public interface EmpresaBuscarDAO {

  Empresa buscaEmpresaPorNome(final String nomeEmpresa) throws Exception;

  List<Empresa> buscaEmpresasPorNome(
      final String nomeEmpresa,
      final Integer limit,
      final Long offset) throws Exception;

  Long quantidadeEmpresa();

  List<Empresa> buscaEmpresasPorNome(final int pageSize, final long offset);

}
