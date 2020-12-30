package com.ricardococati.carga.adapters.repositories;

import com.ricardococati.carga.entities.dto.CotacaoDTO;

public interface CotacaoInserirDAO {

  Boolean incluirCotacao(CotacaoDTO cotacaoDTO) throws Exception;

}
