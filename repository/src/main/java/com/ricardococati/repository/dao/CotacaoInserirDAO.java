package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.CotacaoDTO;

public interface CotacaoInserirDAO {

  Boolean incluirCotacao(CotacaoDTO cotacaoDTO) throws Exception;

}
