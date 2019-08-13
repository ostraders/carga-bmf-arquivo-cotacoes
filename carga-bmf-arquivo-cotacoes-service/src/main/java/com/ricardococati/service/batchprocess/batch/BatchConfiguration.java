package com.ricardococati.service.batchprocess.batch;

import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.enums.CaminhoArquivoEnum;
import com.ricardococati.service.batchprocess.decider.BMFCargaDecider;
import com.ricardococati.service.batchprocess.processor.BMFCargaItemProcessor;
import com.ricardococati.service.batchprocess.processor.BMFCargaValidaEstruturaArquivoProcessor;
import com.ricardococati.service.batchprocess.read.BMFCargaItemReader;
import com.ricardococati.service.batchprocess.tasklet.MoveArquivosTasklet;
import com.ricardococati.service.batchprocess.validator.VerificadorArquivoSkipper;
import com.ricardococati.service.batchprocess.writer.BMFCargaItemWriter;
import com.ricardococati.service.config.ControleArquivoConfig;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration implements Serializable {

  private static final long serialVersionUID = -8528625581896050611L;

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final ControleArquivoConfig arquivoConfig;

  @Bean
  @StepScope
  public MultiResourceItemReader<FieldSet> bmfItemReader() throws Exception {
    return new BMFCargaItemReader().read();
  }

  @Bean
  public BMFCargaItemProcessor bmfItemProcessor() {
    return new BMFCargaItemProcessor();
  }

  @Bean
  public Job jobExecutionBatch(
      @Qualifier("step1_ValidaEstruturaArquivo") Step step1_ValidaEstruturaArquivo,
      @Qualifier("step2_LerProcessarEscreverArquivo") Step step2_LerProcessarEscreverArquivo,
      @Qualifier("step3_MoverArquivoParaSucesso") Step step3_MoverArquivoParaSucesso,
      @Qualifier("step4_MoverArquivoParaErro") Step step4_MoverArquivoParaErro,
      BMFCargaDecider bmfCargaDecider) throws Exception {
    log.info("Executando jobExecutionBatch [SPRING BATCH]");

    FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("flowJobExecutionBatch");

    Flow flow = flowBuilder
        .start(step1_ValidaEstruturaArquivo)
        .on(FlowExecutionStatus.FAILED.getName())
        .to(step4_MoverArquivoParaErro)
        .from(step1_ValidaEstruturaArquivo)
        .next(bmfCargaDecider)
        .on(FlowExecutionStatus.COMPLETED.getName())
        .to(step2_LerProcessarEscreverArquivo)
        .next(step3_MoverArquivoParaSucesso)
        .from(bmfCargaDecider)
        .on(FlowExecutionStatus.FAILED.getName())
        .to(step4_MoverArquivoParaErro).build();

    return jobBuilderFactory.get("jobExecutionBatch")
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
        .<FieldSet, BMFCargaDTO>chunk(50)
        .reader(bmfItemReader())
        .faultTolerant()
        .skipPolicy(verificadorArquivoSkipper())
        .processor(processorValidaEstruturaArquivo())
        .build();
  }

  @Bean
  public BMFCargaValidaEstruturaArquivoProcessor processorValidaEstruturaArquivo() {
    return new BMFCargaValidaEstruturaArquivoProcessor(arquivoConfig);
  }

  @Bean
  public Step step2_LerProcessarEscreverArquivo() throws Exception {
    return stepBuilderFactory.get("step2_LerProcessarEscreverArquivo")
        .<FieldSet, BMFCargaDTO>chunk(50)
        .reader(bmfItemReader())
        .processor(bmfItemProcessor())
        .writer(bmfItemWriter())
        .build();
  }

  @Bean
  protected Step step3_MoverArquivoParaSucesso() {
    return stepBuilderFactory.get("step3_MoverArquivoParaSucesso")
        .tasklet(new MoveArquivosTasklet(
            CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO,
            CaminhoArquivoEnum.CAMINHO_ARQUIVO_SUCESSO))
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
  public BMFCargaItemWriter bmfItemWriter() {
    return new BMFCargaItemWriter();
  }

}
