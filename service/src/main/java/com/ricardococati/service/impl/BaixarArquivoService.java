package com.ricardococati.service.impl;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.repository.dao.ICalendarioFeriadoDAO;
import com.ricardococati.service.IBaixarArquivoService;
import com.ricardococati.service.config.ControleArquivoConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
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

  @Override
  public Boolean baixaArquivoCotacao() throws IOException {
    boolean hojeEhSabado = hojeEh(DayOfWeek.SATURDAY);
    boolean hojeEhDomingo = hojeEh(DayOfWeek.SUNDAY);
    boolean hojeEhFeriado = feriadoDAO.buscaCalendarioFeriado(LocalDate.now());
    if(hojeEhSabado || hojeEhDomingo || hojeEhFeriado){
      return Boolean.FALSE;
    }
    Date dataPregao = formatarDateReaderDate();
    final String dataFormatada = sdf.format(dataPregao);
    final String caminho = CaminhoArquivoEnum.CAMINHO_ARQUIVO_ZIP.getCaminho();
    Boolean arquivoPronto = efetuaDownloadArquivo(dataFormatada, caminho);
    if (arquivoPronto) {
      arquivoPronto = descompactarArquivoBaixado(dataFormatada);
    }
    return arquivoPronto;
  }

  private Boolean descompactarArquivoBaixado(final String dataFormatada) throws IOException {
    File destDir = new File(
        CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho()
    );
    byte[] buffer = new byte[1024];
    ZipInputStream zis = new ZipInputStream(
            new FileInputStream(
                CaminhoArquivoEnum.CAMINHO_ARQUIVO_ZIP.getCaminho() +
                    NOME_ARQUIVO_DEFAULT +
                    dataFormatada +
                    ".zip"
            )
        );
    ZipEntry zipEntry = zis.getNextEntry();
    while (zipEntry != null) {
      File newFile = newFile(destDir, zipEntry);
      FileOutputStream fos = new FileOutputStream(newFile);
      int len;
      while ((len = zis.read(buffer)) > 0) {
        fos.write(buffer, 0, len);
      }
      fos.close();
      zipEntry = zis.getNextEntry();
    }
    zis.closeEntry();
    zis.close();
    return Boolean.TRUE;
  }

  public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
    File destFile = new File(destinationDir, zipEntry.getName());

    String destDirPath = destinationDir.getCanonicalPath();
    String destFilePath = destFile.getCanonicalPath();

    if (!destFilePath.startsWith(destDirPath + File.separator)) {
      throw new IOException("A entrada está fora do diretório de destino: " + zipEntry.getName());
    }

    return destFile;
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

  private boolean hojeEh(final DayOfWeek dayOfWeek) {
    return LocalDate
        .now(ZoneId.of("America/Sao_Paulo"))
        .getDayOfWeek()
        .equals(dayOfWeek);
  }

  public Date formatarDateReaderDate() {
    LocalDateTime dataStr = LocalDateTime.now();
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
