package com.ricardococati.service.impl;

import static java.util.Objects.nonNull;

import com.ricardococati.service.IGerenciadorArquivosService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ricardococati.model.enums.CaminhoArquivoEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GerenciadorArquivosService implements IGerenciadorArquivosService, Serializable {

  private static final long serialVersionUID = -1283658257967124514L;
  private static final String LOCK = "@.lock";
  private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

  @Override
  public void moverArquivosEntreDiretorios(String origem, String destino)
      throws Exception, FileNotFoundException, IOException {
    File arquivosDiretorioOrigem = new File(origem);
    File diretorioDestino = new File(destino);
    File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();
    File arquivo;
    int i = 0;
    if (arrayArquivos.length > 0) {
      for (int j = arrayArquivos.length; i < j; i++) {
        arquivo = arrayArquivos[i];
        moveArquivoParaDiretorio(diretorioDestino, arquivo);
      }
    }
  }

  @Override
  public void moverArquivosEntreDiretoriosVerificaDiretorio(String origem, String destino)
      throws Exception, FileNotFoundException, IOException {
    File arquivosDiretorioOrigem = new File(origem);
    File diretorioDestino = new File(destino);
    File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();
    File arquivo;
    int i = 0;
    if (nonNull(arrayArquivos) && arrayArquivos.length > 0) {
      for (int j = arrayArquivos.length; i < j; i++) {
        arquivo = arrayArquivos[i];
        moveArquivoParaDiretorio(diretorioDestino, arquivo);
      }
    } else {
      log.info("Não existem arquivos no diretório!");
    }
  }

  @Override
  public void moveArquivoParaDiretorio(File diretorioDestino, File arquivo)
      throws Exception, FileNotFoundException, IOException {
    arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));
    log.info(
        "Arquivo movido para o diretório - " + diretorioDestino.getPath() + "/" + arquivo.getName()
            + " às " + sdf.format(new Date()));
  }

  @Override
  public File renomearArquivo(File arquivo) {
    arquivo.renameTo(new File(CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho(),
        arquivo.getName() + LOCK));
    arquivo = new File(CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA.getCaminho(),
        arquivo.getName() + LOCK);
    return arquivo;
  }

  @Override
  public Boolean validarArquivoLock(File arquivo) {
    Boolean retorno = Boolean.FALSE;
    if (arquivo.getName().contains(LOCK)) {
      retorno = Boolean.TRUE;
    }
    return retorno;
  }

  @Override
  public void removeLock(File diretorioDestino, File arquivo) {
    arquivo.renameTo(new File(diretorioDestino, arquivo.getName().replaceAll(LOCK, "")));
  }

}
