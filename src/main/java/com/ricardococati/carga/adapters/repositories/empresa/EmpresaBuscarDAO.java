package com.ricardococati.carga.adapters.repositories.empresa;

import com.ricardococati.carga.entities.domains.empresa.Empresa;

public interface EmpresaBuscarDAO {

  Empresa buscaEmpresaPorNome(final String nomeEmpresa) throws Exception;

}
