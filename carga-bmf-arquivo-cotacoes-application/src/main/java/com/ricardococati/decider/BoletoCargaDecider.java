package com.ricardococati.decider;

import java.io.Serializable;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ricardococati.service.impl.IntegrationService;

@Component
public class BoletoCargaDecider implements JobExecutionDecider, Serializable{
	
	private static final long serialVersionUID = 488805782785072196L;
	
	@Autowired
	private IntegrationService integrationService;
	
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		
		if(integrationService.isArquivoValido()){
			return FlowExecutionStatus.COMPLETED;
		}else{
			return FlowExecutionStatus.FAILED;
		}		
	}
}
