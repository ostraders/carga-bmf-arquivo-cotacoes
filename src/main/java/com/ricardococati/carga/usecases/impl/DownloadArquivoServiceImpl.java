package com.ricardococati.carga.usecases.impl;

import com.ricardococati.carga.config.ControleArquivoConfig;
import com.ricardococati.carga.usecases.DownloadArquivoService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DownloadArquivoServiceImpl implements DownloadArquivoService {

  private static final String NOME_ARQUIVO_DEFAULT = "COTAHIST_D";
  private final ControleArquivoConfig arquivoConfig;

  @Override
  public String doanloadArquivo(final String dataFormatada, final String caminho) throws IOException {
    String retorno = "";
    try {
      final String url = arquivoConfig.getUrlArquivoCotacoes().replace("*", dataFormatada);
      final String nomeArquivo = NOME_ARQUIVO_DEFAULT + dataFormatada + ".zip";
      FileUtils.copyURLToFile(
          new URL(url),
          new File(caminho + nomeArquivo),
          10000,
          10000
      );
      retorno = url + nomeArquivo;
    } catch (FileNotFoundException e) {
      log.error("Erro ao tentar baixar arquivo de cotação! {} ", e.getMessage());
      throw e;
    } catch (IOException e) {
      log.error("Erro ao tentar baixar arquivo de cotação! {} ", e.getMessage());
      throw e;
    }
    return retorno;
  }

}
