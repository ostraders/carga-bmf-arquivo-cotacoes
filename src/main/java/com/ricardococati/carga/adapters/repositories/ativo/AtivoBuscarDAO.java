package com.ricardococati.carga.adapters.repositories.ativo;

import com.ricardococati.carga.entities.domains.ativo.Ativo;
import java.util.List;

public interface AtivoBuscarDAO {

  List<Ativo> buscaAtivo() throws Exception;

}
