package com.ricardococati.service.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.kafka.topic.TopicEnum;
import com.ricardococati.model.dto.BMFCargaDTO;
import com.ricardococati.model.dto.CandlestickDiarioDTO;
import com.ricardococati.model.dto.CandlestickDiarioMessage;
import com.ricardococati.model.dto.Cotacao;
import com.ricardococati.model.dto.CotacaoDTO;
import com.ricardococati.model.dto.Header;
import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.repository.dao.ICandlestickDiarioDAO;
import com.ricardococati.repository.dao.ICotacaoDAO;
import com.ricardococati.repository.dao.IHeaderDAO;
import com.ricardococati.repository.event.PostgresEventListener;
import com.ricardococati.service.IBMFCargaService;
import com.ricardococati.service.config.ControleArquivoConfig;
import com.ricardococati.service.converter.CandlestickConverter;
import com.ricardococati.service.converter.CotacaoConverter;
import com.ricardococati.service.converter.HeaderConverter;
import com.ricardococati.service.util.ControlaIdArquivoUtil;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class BMFCargaService implements IBMFCargaService {

  private static final String LOTE_PADRAO = "02";
  private final ICandlestickDiarioDAO candlestickDiarioDAO;
  private final ControleArquivoConfig arquivoConfig;
  private final CandlestickConverter candlestickConverter;
  private final IHeaderDAO headerDAO;
  private final ICotacaoDAO cotacaoDAO;
  private final HeaderConverter convertHed;
  private final CotacaoConverter convertCot;
  private final ControlaIdArquivoUtil idArquivoUtil;
  private final PostgresEventListener listener;

  @Override
  public void insereDados(List<? extends BMFCargaDTO> listCargaDTO) {
    try {
      if (nonNull(listCargaDTO) && !listCargaDTO.isEmpty()) {
        for (BMFCargaDTO bmfCargaDTO : listCargaDTO) {
          if (Header.class.isInstance(bmfCargaDTO)) {
            HeaderDTO headerDTO = convertHed.convert((Header) bmfCargaDTO);
            headerDTO.setIdentificacaoArquivo(idArquivoUtil.getIdentificadorArquivo());
            headerDAO.incluirHeaderArquivo(headerDTO);
          } else if (Cotacao.class.isInstance(bmfCargaDTO)) {
            Cotacao cotacao = (Cotacao) bmfCargaDTO;
            CotacaoDTO cotacaoDTO = convertCot.convert(cotacao);
            if(LOTE_PADRAO.equals(cotacaoDTO.getCodbdi())) {
              cotacaoDTO.setIdentificacaoArquivo(idArquivoUtil.getIdentificadorArquivo());
              cotacaoDAO.incluirCotacao(cotacaoDTO);
              log.info("Incluido cotacao: {}", cotacao);
              CandlestickDiarioDTO candlestickDiarioDTO = candlestickConverter.convert(cotacao);
              if (nonNull(candlestickDiarioDTO)) {
                candlestickDiarioDAO.incluirCandlestickDiario(candlestickDiarioDTO);
                CandlestickDiarioMessage message = candlestickConverter.convertMessage(candlestickDiarioDTO);
                listener.onAfterSave(message, TopicEnum.CANDLESTICK_DIARIO.getTopicName());
              }
            }
          }
        }
      }
    } catch (Exception e) {
      arquivoConfig.setArquivoValido(false);
      log.error("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: {}" + e.getMessage());
    }
  }

}
