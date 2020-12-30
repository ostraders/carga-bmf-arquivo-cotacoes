package com.ricardococati.carga.usecases.batchprocess.tasklet;

import com.ricardococati.carga.entities.enums.CaminhoArquivoEnum;
import com.ricardococati.carga.usecases.GerenciadorArquivosService;
import com.ricardococati.carga.usecases.impl.GerenciadorArquivosServiceImpl;
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
    GerenciadorArquivosService gerenciadorArquivos = new GerenciadorArquivosServiceImpl();
    gerenciadorArquivos
        .moverArquivosEntreDiretoriosVerificaDiretorio(getCaminhoEntrada().getCaminho(),
            getCaminhoErro().getCaminho());
    return RepeatStatus.FINISHED;
  }

}
