package com.ricardococati.service.impl;

import com.ricardococati.kafka.topic.TopicEnum;
import com.ricardococati.model.dto.CotacaoDTO;
import com.ricardococati.model.entities.Ativo;
import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.model.entities.CandlestickDiarioMessage;
import com.ricardococati.model.entities.Cotacao;
import com.ricardococati.repository.dao.AtivoBuscarDAO;
import com.ricardococati.repository.dao.CandlestickDiarioInserirDAO;
import com.ricardococati.repository.dao.CotacaoInserirDAO;
import com.ricardococati.repository.event.CandlestickEventListener;
import com.ricardococati.service.BMFCargaCotacaoService;
import com.ricardococati.service.config.ControleArquivoConfig;
import com.ricardococati.service.converter.CandlestickConverter;
import com.ricardococati.service.converter.CotacaoConverter;
import com.ricardococati.service.util.ControlaIdArquivoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class BMFCargaCotacaoServiceImpl implements BMFCargaCotacaoService {

  private static final String LOTE_PADRAO = "02";
  private final CandlestickDiarioInserirDAO candlestickDiarioDAO;
  private final ControleArquivoConfig arquivoConfig;
  private final CandlestickConverter candlestickConverter;
  private final CotacaoInserirDAO cotacaoInserirDAO;
  private final AtivoBuscarDAO ativoBuscarDAO;
  private final CotacaoConverter convertCot;
  private final ControlaIdArquivoUtil idArquivoUtil;
  private final CandlestickEventListener listener;

  @Override
  public Boolean insereDados(final Cotacao cotacao) throws Exception {
    Boolean retorno = Boolean.FALSE;
    try {
      CotacaoDTO cotacaoDTO = convertCot.convert(cotacao);
      if (isLotePadrao(cotacaoDTO) && isAtivoValido(cotacaoDTO)) {
        cotacaoDTO.setIdentificacaoArquivo(idArquivoUtil.getIdentificadorArquivo());
        cotacaoInserirDAO.incluirCotacao(cotacaoDTO);
        log.info("Incluido cotacao: {}", cotacao);
        CandlestickDiario candlestickDiarioDTO = candlestickConverter.convert(cotacao);
        if (nonNull(candlestickDiarioDTO)) {
          candlestickDiarioDAO.incluirCandlestickDiario(candlestickDiarioDTO);
          CandlestickDiarioMessage message = candlestickConverter
              .convertMessage(candlestickDiarioDTO);
          listener.onAfterSave(message, TopicEnum.CANDLESTICK_DIARIO.getTopicName());
        }
        retorno = Boolean.TRUE;
      }
    } catch (Exception e) {
      arquivoConfig.setArquivoValido(false);
      log.error("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: {} ", e.getMessage());
      throw new Exception("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE");
    }
    return retorno;
  }

  private Boolean isAtivoValido(final CotacaoDTO cotacao) throws Exception {
    return buscaAtivos()
        .stream()
        .filter(Objects::nonNull)
        .map(Ativo::getAtivo)
        .anyMatch(ativo ->
            nonNull(cotacao.getCodneg())
                && cotacao.getCodneg().equals(ativo)
        );
  }

  private Boolean isLotePadrao(final CotacaoDTO cotacao){
    return LOTE_PADRAO.equals(cotacao.getCodbdi());
  }

  @Cacheable
  private List<Ativo> buscaAtivos() throws Exception {
    return ativoBuscarDAO.buscaAtivo();
  }

}
