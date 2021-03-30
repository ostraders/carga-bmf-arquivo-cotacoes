package com.ricardococati.carga.usecases.empresa.impl;

import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import com.ricardococati.carga.usecases.empresa.InserirEmpresa;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InserirEmpresaImpl implements InserirEmpresa {

  @Override
  public void salvar(final List<EmpresaDTO> empresaList) {
    System.out.println(empresaList.toString());
  }

}
