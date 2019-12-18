package com.ricardococati.service.impl;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.repository.dao.ICalendarioFeriadoDAO;
import com.ricardococati.service.IBaixarArquivoService;
import com.ricardococati.service.IDescompactarArquivoService;
import com.ricardococati.service.config.ControleArquivoConfig;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaixarArquivoService implements IBaixarArquivoService {

  private static final String NOME_ARQUIVO_DEFAULT = "COTAHIST_D";
  private final ControleArquivoConfig arquivoConfig;
  private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
  private final ICalendarioFeriadoDAO feriadoDAO;
  private final IDescompactarArquivoService descompactarService;

  @Override
  public Boolean baixaArquivoCotacao() throws IOException {
    final String dataFormatada = obterDataDiaUtilStr();
    final String caminho = CaminhoArquivoEnum.CAMINHO_ARQUIVO_ZIP.getCaminho();
    final String nomeArquivo = caminho + NOME_ARQUIVO_DEFAULT + dataFormatada + ".zip";
    Boolean arquivoPronto = Boolean.FALSE;
    if(!"".equals(dataFormatada)){
      arquivoPronto = efetuaDownloadArquivo(dataFormatada, caminho);
      if (arquivoPronto) {
        log.info("Arquivo baixado com sucesso: {} ", nomeArquivo);
        arquivoPronto = descompactarService.descompactaArquivoCotacao(nomeArquivo);
      }
    }
    return arquivoPronto;
  }

  private Boolean efetuaDownloadArquivo(String dataFormatada, String caminho) throws IOException {
    try {
      FileUtils.copyURLToFile(
          new URL(arquivoConfig.getUrlArquivoCotacoes().replace("*", dataFormatada)),
          new File(caminho + NOME_ARQUIVO_DEFAULT +dataFormatada+".zip"),
          10000,
          10000
      );
    } catch (IOException e) {
      log.error("Erro ao tentar baixar arquivo de cotação! {} ", e.getMessage());
      throw e;
    }
    return Boolean.TRUE;
  }

  private String obterDataDiaUtilStr(){
    LocalDate dataAtual = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
    boolean hojeEhSabado = hojeEh(dataAtual.getDayOfWeek(), DayOfWeek.SATURDAY);
    boolean hojeEhDomingo = hojeEh(dataAtual.getDayOfWeek(),DayOfWeek.SUNDAY);
    boolean hojeEhFeriado = feriadoDAO.buscaCalendarioFeriado(dataAtual);
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
