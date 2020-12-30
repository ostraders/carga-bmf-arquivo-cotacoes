package com.ricardococati.carga.adapters.repositories.cotacao;

import com.ricardococati.carga.entities.domains.cotacao.dto.CotacaoDTO;

public interface CotacaoInserirDAO {

  Boolean incluirCotacao(CotacaoDTO cotacaoDTO) throws Exception;

}
