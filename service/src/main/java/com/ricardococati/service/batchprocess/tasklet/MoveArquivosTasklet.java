package com.ricardococati.service.batchprocess.tasklet;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.service.IGerenciadorArquivosService;
import com.ricardococati.service.impl.GerenciadorArquivosService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Data
@AllArgsConstructor
public class MoveArquivosTasklet implements Tasklet {

  private CaminhoArquivoEnum caminhoEntrada;
  private CaminhoArquivoEnum caminhoErro;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {
    IGerenciadorArquivosService gerenciadorArquivos = new GerenciadorArquivosService();
    gerenciadorArquivos
        .moverArquivosEntreDiretoriosVerificaDiretorio(getCaminhoEntrada().getCaminho(),
            getCaminhoErro().getCaminho());
    return RepeatStatus.FINISHED;
  }

}
