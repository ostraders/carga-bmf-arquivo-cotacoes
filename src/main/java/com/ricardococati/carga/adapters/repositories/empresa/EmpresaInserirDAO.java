package com.ricardococati.carga.adapters.repositories.empresa;

import com.ricardococati.carga.entities.domains.empresa.Empresa;

public interface EmpresaInserirDAO {

  Boolean incluirEmpresa(final Empresa empresa) throws Exception;

}
