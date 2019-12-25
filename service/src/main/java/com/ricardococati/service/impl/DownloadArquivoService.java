package com.ricardococati.service.impl;

import com.ricardococati.service.IDownloadArquivoService;
import com.ricardococati.service.config.ControleArquivoConfig;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DownloadArquivoService implements IDownloadArquivoService {

  private static final String NOME_ARQUIVO_DEFAULT = "COTAHIST_D";
  private final ControleArquivoConfig arquivoConfig;

  @Override
  public Boolean doanloadArquivo(final String dataFormatada, final String caminho) throws IOException {
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

}
