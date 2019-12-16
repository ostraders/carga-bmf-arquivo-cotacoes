package com.ricardococati.service.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.service.IBaixarArquivoService;
import com.ricardococati.service.IGerenciadorArquivosService;
import com.ricardococati.service.config.ControleArquivoConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaixarArquivoService implements IBaixarArquivoService {

  private final ControleArquivoConfig arquivoConfig;
  private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

  @Override
  public Boolean baixaArquivoCotacao() {
    final String dataStr = LocalDate.of(2019, 12, 13).toString();
    Date dataPregao = formatarDateReaderDate(dataStr);
    try {

      //connectionTimeout, readTimeout = 10 seconds
      FileUtils.copyURLToFile(
          new URL(arquivoConfig.getUrlArquivoCotacoes().replace("*", sdf.format(dataPregao))),
          new File(CaminhoArquivoEnum.CAMINHO_ARQUIVO_ZIP.getCaminho() + "Teste.zip"), 10000, 10000);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Date formatarDateReaderDate(final String dataStr) {
    SimpleDateFormat formatComTraco = new SimpleDateFormat("yyyy-MM-dd");
    Date data = null;
    Date dataConvertida = null;
    try {
      dataConvertida = formatComTraco.parse(dataStr);
    } catch (ParseException e) {
      log.error("Erro: ", e.getMessage(), e);
    } finally {
      return dataConvertida;
    }
  }

}
