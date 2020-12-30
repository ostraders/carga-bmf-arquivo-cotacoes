package com.ricardococati.carga.adapters.repositories;

import com.ricardococati.carga.entities.domains.Ativo;
import java.util.List;

public interface AtivoBuscarDAO {

  List<Ativo> buscaAtivo() throws Exception;

}
