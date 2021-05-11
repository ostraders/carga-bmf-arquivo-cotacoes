package com.ricardococati.carga.usecases.ativo.impl;

import com.ricardococati.carga.adapters.repositories.ativo.AtivoBuscarDAO;
import com.ricardococati.carga.entities.domains.ativo.Ativo;
import com.ricardococati.carga.usecases.ativo.BuscarAtivo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarAtivoImpl implements BuscarAtivo {

  private final AtivoBuscarDAO ativoBuscarDAO;

  @Override
  public List<Ativo> buscaAtivo(final String ativo) throws Exception {
    return ativoBuscarDAO.buscaAtivo(ativo);
  }

}
