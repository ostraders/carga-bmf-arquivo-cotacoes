package com.ricardococati.service.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.kafka.topic.TopicEnum;
import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.model.entities.CandlestickDiarioMessage;
import com.ricardococati.model.dto.Cotacao;
import com.ricardococati.model.dto.CotacaoDTO;
import com.ricardococati.model.entities.EmpresaAtivo;
import com.ricardococati.repository.dao.CandlestickDiarioDAO;
import com.ricardococati.repository.dao.CotacaoDAO;
import com.ricardococati.repository.dao.EmpresaAtivoDAO;
import com.ricardococati.repository.event.PostgresEventListener;
import com.ricardococati.service.BMFCargaCotacaoService;
import com.ricardococati.service.config.ControleArquivoConfig;
import com.ricardococati.service.converter.CandlestickConverter;
import com.ricardococati.service.converter.CotacaoConverter;
import com.ricardococati.service.util.ControlaIdArquivoUtil;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class BMFCargaCotacaoServiceImpl implements BMFCargaCotacaoService {

  private static final String LOTE_PADRAO = "02";
  private final CandlestickDiarioDAO candlestickDiarioDAO;
  private final ControleArquivoConfig arquivoConfig;
  private final CandlestickConverter candlestickConverter;
  private final CotacaoDAO cotacaoDAO;
  private final EmpresaAtivoDAO empresaAtivoDAO;
  private final CotacaoConverter convertCot;
  private final ControlaIdArquivoUtil idArquivoUtil;
  private final PostgresEventListener listener;

  @Override
  public void insereDados(Cotacao cotacao) {
    try {
      CotacaoDTO cotacaoDTO = convertCot.convert(cotacao);
      if (isLotePadrao(cotacaoDTO) && isAtivoValido(cotacaoDTO)) {
        cotacaoDTO.setIdentificacaoArquivo(idArquivoUtil.getIdentificadorArquivo());
        cotacaoDAO.incluirCotacao(cotacaoDTO);
        log.info("Incluido cotacao: {}", cotacao);
        CandlestickDiario candlestickDiarioDTO = candlestickConverter.convert(cotacao);
        if (nonNull(candlestickDiarioDTO)) {
          candlestickDiarioDAO.incluirCandlestickDiario(candlestickDiarioDTO);
          CandlestickDiarioMessage message = candlestickConverter
              .convertMessage(candlestickDiarioDTO);
          listener.onAfterSave(message, TopicEnum.CANDLESTICK_DIARIO.getTopicName());
        }
      }
    } catch (Exception e) {
      arquivoConfig.setArquivoValido(false);
      log.error("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: {} ", e.getMessage());
    }
  }

  private Boolean isAtivoValido(final CotacaoDTO cotacao){
    return buscaAtivos()
        .stream()
        .filter(Objects::nonNull)
        .map(EmpresaAtivo::getAtivo)
        .anyMatch(ativo ->
            nonNull(cotacao.getCodneg())
                && cotacao.getCodneg().equals(ativo)
        );
  }

  private Boolean isLotePadrao(final CotacaoDTO cotacao){
    return LOTE_PADRAO.equals(cotacao.getCodbdi());
  }

  @Cacheable
  private List<EmpresaAtivo> buscaAtivos() {
    return empresaAtivoDAO.buscaEmpresaAtivo();
  }

}
