package com.ricardococati.carga.usecases.empresa;

import com.ricardococati.carga.config.CustomPageImpl;
import com.ricardococati.carga.entities.domains.empresa.EmpresaDTO;
import com.ricardococati.carga.entities.domains.empresa.EmpresaResponse;
import org.springframework.data.domain.Pageable;

public interface BuscarEmpresa {

  CustomPageImpl<EmpresaResponse> buscarPorNomeEmpresa(final String nomeEmpresa, final Pageable pageable) throws Exception;

}
