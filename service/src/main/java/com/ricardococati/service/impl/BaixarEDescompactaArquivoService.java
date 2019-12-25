package com.ricardococati.service.impl;

import static java.util.Objects.isNull;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.repository.dao.ICalendarioFeriadoDAO;
import com.ricardococati.service.IBaixarEDescompactaArquivoService;
import com.ricardococati.service.IDescompactarArquivoService;
import com.ricardococati.service.IDownloadArquivoService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaixarEDescompactaArquivoService implements IBaixarEDescompactaArquivoService {

  private static final String NOME_ARQUIVO_DEFAULT = "COTAHIST_D";
  private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
  private final ICalendarioFeriadoDAO feriadoDAO;
  private final IDescompactarArquivoService descompactarService;
  private final IDownloadArquivoService downloadService;

  @Override
  public Boolean baixaEDescompactaArquivoCotacao() throws Exception {
    final String dataFormatada = obterDataDiaUtilStr();
    final String caminho = CaminhoArquivoEnum.CAMINHO_ARQUIVO_ZIP.getCaminho();
    final String nomeArquivo = caminho + NOME_ARQUIVO_DEFAULT + dataFormatada + ".zip";
    Boolean arquivoPronto = Boolean.FALSE;
    try{
    if(!"".equals(dataFormatada)){
      arquivoPronto = downloadService.doanloadArquivo(dataFormatada, caminho);
      if (arquivoPronto) {
        log.info("Arquivo baixado com sucesso: {} ", nomeArquivo);
        arquivoPronto = descompactarService.descompactaArquivoCotacao(nomeArquivo);
      }
    }
    } catch (Exception e) {
      log.error("Erro ao baixar e descompactar arquivo: {}", e.getMessage());
      throw new Exception("Erro ao baixar e descompactar arquivo");
    }
    return arquivoPronto;
  }

  private String obterDataDiaUtilStr() throws Exception {
    LocalDate dataAtual = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
    Boolean hojeEhSabado = hojeEh(dataAtual.getDayOfWeek(), DayOfWeek.SATURDAY);
    Boolean hojeEhDomingo = hojeEh(dataAtual.getDayOfWeek(),DayOfWeek.SUNDAY);
    Boolean hojeEhFeriado = feriadoDAO.buscaCalendarioFeriado(dataAtual);
    if(isNull(hojeEhFeriado)){
      throw new Exception("Erro ao obter data dia util");
    }
    if(hojeEhSabado || hojeEhDomingo || hojeEhFeriado){
      return "";
    }
    Date dataPregao = converterDataAtualToDate(dataAtual);
    final String dataFormatada = sdf.format(dataPregao);
    return dataFormatada;
  }

  private boolean hojeEh(final DayOfWeek dataAtual, final DayOfWeek dayOfWeek) {
    return dataAtual.equals(dayOfWeek);
  }

  private Date converterDataAtualToDate(final LocalDate dataAtual) {
    LocalDateTime dataStr = dataAtual.atStartOfDay();
    if(dataStr.getHour() < 19){
      dataStr = dataStr.minusDays(1);
    }
    SimpleDateFormat formatComTraco = new SimpleDateFormat("yyyy-MM-dd");
    Date dataConvertida = null;
    try {
      dataConvertida = formatComTraco.parse(dataStr.toString());
    } catch (ParseException e) {
      log.error("Erro: {}", e.getMessage());
    } finally {
      return dataConvertida;
    }
  }

}
