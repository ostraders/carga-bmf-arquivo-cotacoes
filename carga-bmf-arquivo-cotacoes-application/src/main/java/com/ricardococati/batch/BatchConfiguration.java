package com.ricardococati.batch;

import java.io.Serializable;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ricardococati.decider.BoletoCargaDecider;
import com.ricardococati.dto.BoletoDTO;
import com.ricardococati.enums.CaminhoArquivoEnum;
import com.ricardococati.processor.BoletoItemProcessor;
import com.ricardococati.processor.BoletoValidaEstruturaArquivoProcessor;
import com.ricardococati.read.BoletoItemReader;
import com.ricardococati.tasklet.MoveArquivosTasklet;
import com.ricardococati.validator.VerificadorArquivoSkipper;
import com.ricardococati.writer.BoletoItemWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfiguration implements Serializable{

	private static final long serialVersionUID = -8528625581896050611L;

	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Bean
	@StepScope
	public MultiResourceItemReader<FieldSet> boletoItemReader() throws Exception {
		return new BoletoItemReader().read();
	}

    @Bean
    public BoletoItemProcessor boletoItemProcessor() {
        return new BoletoItemProcessor();
    }

    @Bean
    public Job jobCargaBoleto(@Qualifier("step1_ValidaEstruturaArquivo") Step step1_ValidaEstruturaArquivo,
			@Qualifier("step2_LerProcessarEscreverArquivo") Step step2_LerProcessarEscreverArquivo,
			@Qualifier("step3_MoverArquivoParaSucesso") Step step3_MoverArquivoParaSucesso,
			@Qualifier("step4_MoverArquivoParaErro") Step step4_MoverArquivoParaErro,
			BoletoCargaDecider boletoCargaDecider) throws Exception {
    	log.info("Executando jobCargaBoleto [SPRING BATCH]");
    	
    	FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("flowJobCargaBoleto");
    	
    	Flow flow = flowBuilder
				.start(step1_ValidaEstruturaArquivo)
					.on(FlowExecutionStatus.FAILED.getName())
						.to(step4_MoverArquivoParaErro)
						.from(step1_ValidaEstruturaArquivo)
				.next(boletoCargaDecider)
					.on(FlowExecutionStatus.COMPLETED.getName())
						.to(step2_LerProcessarEscreverArquivo)
						.next(step3_MoverArquivoParaSucesso)
						.from(boletoCargaDecider)
					.on(FlowExecutionStatus.FAILED.getName())
						.to(step4_MoverArquivoParaErro).build();
    	
        return jobBuilderFactory.get("jobCargaBoleto")
                .incrementer(new RunIdIncrementer())
                .start(flow)
                .end()
                .build();
    }
    
	@Bean
	public SkipPolicy verificadorArquivoSkipper() {
	    return new VerificadorArquivoSkipper();
	}
	
	@Bean
	public Step step1_ValidaEstruturaArquivo() throws Exception {
		return stepBuilderFactory.get("step1_ValidaEstruturaArquivo")
				.<FieldSet, BoletoDTO> chunk(50)
			.reader(boletoItemReader())
			.faultTolerant()
			.skipPolicy(verificadorArquivoSkipper())
			.processor(processorValidaEstruturaArquivo())
			.build();
	}
	
	@Bean
	public BoletoValidaEstruturaArquivoProcessor processorValidaEstruturaArquivo() {
		return new BoletoValidaEstruturaArquivoProcessor();
	}

    @Bean
    public Step step2_LerProcessarEscreverArquivo() throws Exception {
        return stepBuilderFactory.get("step2_LerProcessarEscreverArquivo")
                .<FieldSet, BoletoDTO> chunk(50)
                .reader(boletoItemReader())
                .processor(boletoItemProcessor())
                .writer(boletoItemWriter())
                .build();
    }
    
	@Bean
	protected Step step3_MoverArquivoParaSucesso() {
		return stepBuilderFactory.get("step3_MoverArquivoParaSucesso")
				.tasklet(new MoveArquivosTasklet(
						CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO,
						CaminhoArquivoEnum.CAMINHO_ARQUIVO_SAIDA))
				.build();
	}

	@Bean
	protected Step step4_MoverArquivoParaErro() {
		return stepBuilderFactory.get("step4_MoverArquivoParaErro")
				.tasklet(new MoveArquivosTasklet(
						CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO,
						CaminhoArquivoEnum.CAMINHO_ARQUIVO_ERRO))
				.build();
	}

    @Bean
    public BoletoItemWriter boletoItemWriter() {
        return new BoletoItemWriter();
    }

}
