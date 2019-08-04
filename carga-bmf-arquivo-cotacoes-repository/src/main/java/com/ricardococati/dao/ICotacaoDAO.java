package com.ricardococati.dao;

import com.ricardococati.dto.CotacaoDTO;

public interface ICotacaoDAO {

  boolean incluirCotacao(CotacaoDTO cotacaoDTO) throws Exception;

}
