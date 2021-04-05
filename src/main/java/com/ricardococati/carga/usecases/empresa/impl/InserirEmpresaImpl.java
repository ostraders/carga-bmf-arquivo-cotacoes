package com.ricardococati.carga.usecases.empresa.impl;

import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import com.ricardococati.carga.usecases.empresa.InserirEmpresa;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InserirEmpresaImpl implements InserirEmpresa {

  @Override
  public void salvar(final List<EmpresaDTO> empresaList) {
    log.info(empresaList.toString());
  }

}
