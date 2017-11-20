package com.ricardococati.tasklet;

import java.io.IOException;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.ricardococati.diretorios.GerenciadorArquivos;
import com.ricardococati.diretorios.IGerenciadorArquivos;
import com.ricardococati.enums.CaminhoArquivoEnum;

import lombok.Data;

@Data
public class MoveArquivosTasklet implements Tasklet {

	private CaminhoArquivoEnum caminhoEntrada;
	private CaminhoArquivoEnum caminhoErro;
	
	@Autowired
	private IGerenciadorArquivos gerenciadorArquivos;

	public MoveArquivosTasklet(CaminhoArquivoEnum caminhoEntrada, CaminhoArquivoEnum caminhoSaida) {
		super();
		this.caminhoEntrada = caminhoEntrada;
		this.caminhoErro = caminhoSaida;
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws IOException, Exception {
		IGerenciadorArquivos gerenciadorArquivos = new GerenciadorArquivos();
		gerenciadorArquivos.moverArquivosEntreDiretoriosVerificaDiretorio(getCaminhoEntrada().getCaminho(), getCaminhoErro().getCaminho());
    	return RepeatStatus.FINISHED;
	}

}
