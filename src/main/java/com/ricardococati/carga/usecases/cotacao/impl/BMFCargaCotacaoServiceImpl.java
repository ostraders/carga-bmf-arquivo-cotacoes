package com.ricardococati.carga.usecases.cotacao.impl;

import com.ricardococati.carga.adapters.message.event.CandlestickEventListener;
import com.ricardococati.carga.adapters.message.topic.TopicosDiarioSemanal;
import com.ricardococati.carga.adapters.repositories.ativo.AtivoBuscarDAO;
import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickDiarioInserirDAO;
import com.ricardococati.carga.adapters.repositories.cotacao.CotacaoInserirDAO;
import com.ricardococati.carga.config.ControleArquivoConfig;
import com.ricardococati.carga.entities.domains.ativo.Ativo;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiarioMessage;
import com.ricardococati.carga.entities.domains.cotacao.Cotacao;
import com.ricardococati.carga.entities.domains.cotacao.dto.CotacaoDTO;
import com.ricardococati.carga.usecases.cotacao.BMFCargaCotacaoService;
import com.ricardococati.carga.usecases.cotacao.converter.CandlestickConverter;
import com.ricardococati.carga.usecases.cotacao.converter.CotacaoConverter;
import com.ricardococati.carga.utils.ControlaIdArquivoUtil;
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
          listener.onAfterSave(message, TopicosDiarioSemanal.CANDLESTICK_DIARIO.getTopicName());
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
