package com.ricardococati.carga.usecases.batchprocess.decider;

import com.ricardococati.carga.config.ControleArquivoConfig;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BMFCargaDecider implements JobExecutionDecider, Serializable{
	
	private static final long serialVersionUID = 488805782785072196L;

	private final ControleArquivoConfig arquivoConfig;
	
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		if(arquivoConfig.getArquivoValido()){
			return FlowExecutionStatus.COMPLETED;
		}else{
			return FlowExecutionStatus.FAILED;
		}		
	}
}
