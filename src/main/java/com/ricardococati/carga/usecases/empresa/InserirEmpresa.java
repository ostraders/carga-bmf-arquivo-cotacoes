package com.ricardococati.carga.usecases.empresa;

import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import java.util.List;

public interface InserirEmpresa {

  void salvar(final List<EmpresaDTO> empresaList);

}
