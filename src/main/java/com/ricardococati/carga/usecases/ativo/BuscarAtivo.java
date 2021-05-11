package com.ricardococati.carga.usecases.ativo;

import com.ricardococati.carga.entities.domains.ativo.Ativo;
import java.util.List;

public interface BuscarAtivo {

  List<Ativo> buscaAtivo(final String ativo) throws Exception;

}
