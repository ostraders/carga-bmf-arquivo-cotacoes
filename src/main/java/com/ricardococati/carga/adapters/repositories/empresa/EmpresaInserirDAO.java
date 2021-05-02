package com.ricardococati.carga.adapters.repositories.empresa;

import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;

public interface EmpresaInserirDAO {

  Boolean incluirEmpresa(EmpresaDTO empresaDTO) throws Exception;

}
