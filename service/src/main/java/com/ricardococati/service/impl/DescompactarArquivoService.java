package com.ricardococati.service.impl;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.service.IDescompactarArquivoService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DescompactarArquivoService implements IDescompactarArquivoService {

  private static final String NOME_ARQUIVO_DEFAULT = "COTAHIST_D";

  @Override
  public Boolean descompactaArquivoCotacao(final String nomeArquivo) throws IOException {
    File destDir = new File(
        CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho()
    );
    byte[] buffer = new byte[1024];
    ZipInputStream zis = new ZipInputStream(new FileInputStream(nomeArquivo));
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

}
