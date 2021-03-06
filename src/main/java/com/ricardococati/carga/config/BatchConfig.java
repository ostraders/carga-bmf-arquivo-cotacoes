package com.ricardococati.carga.config;

import static com.ricardococati.carga.entities.enums.CaminhoArquivoEnum.CAMINHO_ARQUIVO_ERRO;
import static com.ricardococati.carga.entities.enums.CaminhoArquivoEnum.CAMINHO_ARQUIVO_EXECUCAO;
import static com.ricardococati.carga.entities.enums.CaminhoArquivoEnum.CAMINHO_ARQUIVO_SUCESSO;

import com.ricardococati.carga.entities.domains.arquivo.Arquivo;
import com.ricardococati.carga.usecases.batchprocess.decider.CargaDecider;
import com.ricardococati.carga.usecases.batchprocess.processor.CargaItemProcessor;
import com.ricardococati.carga.usecases.batchprocess.processor.CargaValidaEstruturaArquivoProcessor;
import com.ricardococati.carga.usecases.batchprocess.read.CargaItemReader;
import com.ricardococati.carga.usecases.batchprocess.tasklet.MoveArquivosTasklet;
import com.ricardococati.carga.usecases.batchprocess.validator.VerificadorArquivoSkipper;
import com.ricardococati.carga.usecases.batchprocess.writer.CargaItemWriter;
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
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final ControleArquivoConfig arquivoConfig;
  private final CargaItemWriter itemWriter;
  private final CargaItemReader itemReader;
  private final CargaItemProcessor itemProcessor;

  @Bean
  public Job jobExecutionBatch(
      @Qualifier("step1_ValidaEstruturaArquivo") Step step1_ValidaEstruturaArquivo,
      @Qualifier("step2_LerProcessarEscreverArquivo") Step step2_LerProcessarEscreverArquivo,
      @Qualifier("step3_MoverArquivoParaSucesso") Step step3_MoverArquivoParaSucesso,
      @Qualifier("step4_MoverArquivoParaErro") Step step4_MoverArquivoParaErro,
      CargaDecider cargaDecider) throws Exception {
    log.info("Executando jobExecutionBatch [SPRING BATCH]");

    FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("flowJobExecutionBatch");

    Flow flow = flowBuilder
        .start(step1_ValidaEstruturaArquivo)
        .on(FlowExecutionStatus.FAILED.getName())
        .to(step4_MoverArquivoParaErro)
        .from(step1_ValidaEstruturaArquivo)
        .next(cargaDecider)
        .on(FlowExecutionStatus.COMPLETED.getName())
        .to(step2_LerProcessarEscreverArquivo)
        .next(step3_MoverArquivoParaSucesso)
        .from(cargaDecider)
        .on(FlowExecutionStatus.FAILED.getName())
        .to(step4_MoverArquivoParaErro).build();

    return jobBuilderFactory.get("jobExecutionBatch")
        .incrementer(new RunIdIncrementer())
        .start(flow)
        .end()
        .build();
  }

  @Bean
  public Step step1_ValidaEstruturaArquivo() throws Exception {
    return stepBuilderFactory.get("step1_ValidaEstruturaArquivo")
        .<FieldSet, Arquivo>chunk(50)
        .reader(bmfItemReader())
        .faultTolerant()
        .skipPolicy(verificadorArquivoSkipper())
        .processor(processorValidaEstruturaArquivo())
        .build();
  }

  @Bean
  public CargaValidaEstruturaArquivoProcessor processorValidaEstruturaArquivo() {
    return new CargaValidaEstruturaArquivoProcessor(arquivoConfig);
  }

  @Bean
  public Step step2_LerProcessarEscreverArquivo() throws Exception {
    return stepBuilderFactory.get("step2_LerProcessarEscreverArquivo")
        .<FieldSet, Arquivo>chunk(50)
        .reader(bmfItemReader())
        .processor(bmfItemProcessor())
        .writer(bmfItemWriter())
        .build();
  }

  @Bean
  @StepScope
  public MultiResourceItemReader<FieldSet> bmfItemReader() throws Exception {
    return itemReader.read();
  }

  @Bean
  public CargaItemProcessor bmfItemProcessor() {
    return itemProcessor;
  }

  @Bean
  public SkipPolicy verificadorArquivoSkipper() {
    return new VerificadorArquivoSkipper();
  }

  @Bean
  protected Step step3_MoverArquivoParaSucesso() {
    return stepBuilderFactory.get("step3_MoverArquivoParaSucesso")
        .tasklet(new MoveArquivosTasklet(
            CAMINHO_ARQUIVO_EXECUCAO,
            CAMINHO_ARQUIVO_SUCESSO))
        .build();
  }

  @Bean
  protected Step step4_MoverArquivoParaErro() {
    return stepBuilderFactory.get("step4_MoverArquivoParaErro")
        .tasklet(new MoveArquivosTasklet(
            CAMINHO_ARQUIVO_EXECUCAO,
            CAMINHO_ARQUIVO_ERRO))
        .build();
  }

  @Bean
  public CargaItemWriter bmfItemWriter() {
    return itemWriter;
  }

}
