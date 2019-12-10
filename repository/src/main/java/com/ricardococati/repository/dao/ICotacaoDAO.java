package com.ricardococati.repository.dao;

import com.ricardococati.model.dto.CotacaoDTO;
import com.ricardococati.model.dto.SplitInplit;

public interface ICotacaoDAO {

  boolean incluirCotacao(CotacaoDTO cotacaoDTO) throws Exception;

  Boolean split(SplitInplit splitInplit);

  Boolean inplit(SplitInplit splitInplit);
}
