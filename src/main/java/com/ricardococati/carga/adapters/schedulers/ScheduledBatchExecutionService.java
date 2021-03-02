package com.ricardococati.carga.adapters.schedulers;

import static com.ricardococati.carga.entities.enums.CaminhoArquivoEnum.CAMINHO_ARQUIVO_ENTRADA;

import com.ricardococati.carga.usecases.integracao.IntegracaoService;
import com.ricardococati.carga.utils.ControlaIdArquivoUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledBatchExecutionService {

  private final IntegracaoService service;
  private final ControlaIdArquivoUtil idArquivoUtil;
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

  @Scheduled(cron = "0 0/1 * * * *")
  public void executaAgendador() {
    log.info("Inicia execução PROCESSO BATCH em " + sdf.format(new Date()));
    try {
      if (existemArquivosNoDiretorio()) {
        executaGeracaoCandleDiario();
      }
    } catch (Exception e) {
      log.error(" Causa: " + e.getCause() + " Mensagem de Erro: " + e.getMessage());
    }
    log.info("Termina execução PROCESSO BATCH em " + sdf.format(new Date()));
  }

  private Boolean existemArquivosNoDiretorio() {
    final String caminho = CAMINHO_ARQUIVO_ENTRADA.getCaminho();
    File arquivosDiretorioOrigem = new File(caminho);
    File arrayArquivos[] = arquivosDiretorioOrigem.listFiles();
    if (arquivosDiretorioOrigem.exists() && arrayArquivos.length > 0) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  private void executaGeracaoCandleDiario() throws Exception {
    idArquivoUtil.setIdentificadorArquivo(getIdArquivo());
    service.execute();
  }

  private Long getIdArquivo() throws Exception {
    return service.getIdArquivoSequence("ARQUIVO_SEQ");
  }

}
