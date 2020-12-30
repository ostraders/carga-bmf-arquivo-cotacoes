package com.ricardococati.carga.usecases.arquivo.impl;

import static com.ricardococati.carga.entities.enums.CaminhoArquivoEnum.CAMINHO_ARQUIVO_ZIP;
import static java.util.Objects.isNull;

import com.ricardococati.carga.adapters.repositories.calendario.CalendarioFeriadoDAO;
import com.ricardococati.carga.entities.response.BaixarArquivo;
import com.ricardococati.carga.usecases.arquivo.BaixarArquivoService;
import com.ricardococati.carga.usecases.arquivo.DescompactarArquivoService;
import com.ricardococati.carga.usecases.arquivo.DownloadArquivoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaixarArquivoServiceImpl implements BaixarArquivoService {

  private static final String NOME_ARQUIVO_DEFAULT = "COTAHIST_D";
  private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
  private final CalendarioFeriadoDAO feriadoDAO;
  private final DescompactarArquivoService descompactarService;
  private final DownloadArquivoService downloadService;

  @Override
  public BaixarArquivo baixarArquivoCotacao(final LocalDate dtpreg) throws Exception {
    LocalDateTime dtLocal = dtpreg.atTime(20, 00);
    final String dataFormatada = validarDataSeEhDiaUtilStr(dtLocal);
    return obterArquivo(dataFormatada);
  }

  private BaixarArquivo obterArquivo(final String dataFormatada) throws Exception {
    final String caminho = CAMINHO_ARQUIVO_ZIP.getCaminho();
    BaixarArquivo baixarArquivo = BaixarArquivo
        .builder()
        .caminhoArquivoLocal((caminho + NOME_ARQUIVO_DEFAULT + dataFormatada + ".zip"))
        .build();
    try {
      if (!"".equals(dataFormatada)) {
        baixarArquivo.setUrl(downloadService.doanloadArquivo(dataFormatada, caminho));
        if (!"".equals(baixarArquivo)) {
          log.info("Arquivo baixado com sucesso: {} ", baixarArquivo.getCaminhoArquivoLocal());
          descompactarService.descompactaArquivoCotacao(baixarArquivo.getCaminhoArquivoLocal());
        }
      }
    } catch (Exception e) {
      log.error("Erro ao baixar e descompactar arquivo: {}", e.getMessage());
      throw new Exception("Erro ao baixar e descompactar arquivo");
    }
    return baixarArquivo;
  }

  private String validarDataSeEhDiaUtilStr(final LocalDateTime dtpreg) throws Exception {
    Boolean hojeEhSabado = hojeEh(dtpreg.getDayOfWeek(), DayOfWeek.SATURDAY);
    Boolean hojeEhDomingo = hojeEh(dtpreg.getDayOfWeek(), DayOfWeek.SUNDAY);
    Boolean hojeEhFeriado = feriadoDAO.buscaCalendarioFeriado(dtpreg.toLocalDate());
    if (isNull(hojeEhFeriado)) {
      throw new Exception("Erro ao obter data dia util");
    }
    if (hojeEhSabado || hojeEhDomingo || hojeEhFeriado) {
      return "";
    }
    Date dataPregao = converterDataAtualToDate(dtpreg);
    final String dataFormatada = sdf.format(dataPregao);
    return dataFormatada;
  }

  private boolean hojeEh(final DayOfWeek dataAtual, final DayOfWeek dayOfWeek) {
    return dataAtual.equals(dayOfWeek);
  }

  private Date converterDataAtualToDate(final LocalDateTime dataAtual) {
    LocalDateTime dataStr = dataAtual;
    if (dataStr.getHour() < 19) {
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
