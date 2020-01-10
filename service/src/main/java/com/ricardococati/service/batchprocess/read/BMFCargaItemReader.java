package com.ricardococati.service.batchprocess.read;

import com.ricardococati.model.enums.CaminhoArquivoEnum;
import com.ricardococati.service.batchprocess.layouts.CotacoesDosPapeisPorDiaLayoutImpl;
import com.ricardococati.service.batchprocess.layouts.HeaderBMFLayoutImpl;
import com.ricardococati.service.batchprocess.layouts.TraillerBMFLayoutImpl;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceArrayPropertyEditor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BMFCargaItemReader implements ItemReader<MultiResourceItemReader<FieldSet>>, ItemStream {

	private final static String HEADER = "00*";
	private final static String COTACAO = "01*";
	private final static String TRAILER = "99*";
	private FlatFileItemReader<FieldSet> flatFileItemReader;
	private MultiResourceItemReader<FieldSet> multiResourceItemReader;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		flatFileItemReader = new FlatFileItemReader<>();
	}

	@AfterStep
	public void afterStep(StepExecution stepExecution) {
		flatFileItemReader.close();
		multiResourceItemReader.close();
	}

	@Override
	public void open(ExecutionContext arg0) throws ItemStreamException {
		multiResourceItemReader = new MultiResourceItemReader<>();
		multiResourceItemReader.setResources(getArquivos());
		multiResourceItemReader.setStrict(false);
		multiResourceItemReader.open(arg0);
		flatFileItemReader.setStrict(true);
	}

	@Override
	public MultiResourceItemReader<FieldSet> read() throws Exception {
		flatFileItemReader = new FlatFileItemReader<>();
		multiResourceItemReader = new MultiResourceItemReader<>();
		multiResourceItemReader.setResources(getArquivos());
		multiResourceItemReader.setStrict(false);
		flatFileItemReader.setStrict(true);
		final DefaultLineMapper<FieldSet> defaultLineMapper = new DefaultLineMapper<>();
		final PatternMatchingCompositeLineTokenizer orderFileTokenizer = new PatternMatchingCompositeLineTokenizer();
		orderFileTokenizer.setTokenizers(setTokenizers());
		defaultLineMapper.setLineTokenizer(orderFileTokenizer);

		defaultLineMapper.setFieldSetMapper(new PassThroughFieldSetMapper());

		flatFileItemReader.setLineMapper(defaultLineMapper);
		flatFileItemReader.afterPropertiesSet();
		defaultLineMapper.afterPropertiesSet();
		multiResourceItemReader.setDelegate(flatFileItemReader);
		return multiResourceItemReader;
	}

	private Map<String, LineTokenizer> setTokenizers() {
		final Map<String, LineTokenizer> tokenizers = new HashMap<>();

		HeaderBMFLayoutImpl headerLayout = new HeaderBMFLayoutImpl();
		tokenizers.put(HEADER, headerLayout.configurarParser());

		CotacoesDosPapeisPorDiaLayoutImpl cotacao = new CotacoesDosPapeisPorDiaLayoutImpl();
		tokenizers.put(COTACAO, cotacao.configurarParser());

		TraillerBMFLayoutImpl trailerLayout = new TraillerBMFLayoutImpl();
		tokenizers.put(TRAILER, trailerLayout.configurarParser());

		return tokenizers;
	}

	private Resource[] getArquivos() {
		ResourceArrayPropertyEditor resourceLoader = new ResourceArrayPropertyEditor();
		resourceLoader.setAsText(CaminhoArquivoEnum.CAMINHO_ARQUIVO_PROCESSO.getCaminho());
		Resource[] resources = (Resource[]) resourceLoader.getValue();
		return resources;
	}

	@Override
	public void close() throws ItemStreamException {
		//VOID METHOD
	}

	@Override
	public void update(ExecutionContext arg0) throws ItemStreamException {
		//VOID METHOD
	}

}
