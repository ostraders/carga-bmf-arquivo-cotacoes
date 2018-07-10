package com.ricardococati.read;

import com.ricardococati.enums.CaminhoArquivoEnum;
import com.ricardococati.layouts.CotacoesDosPapeisPorDiaLayout;
import com.ricardococati.layouts.HeaderBMFLayout;
import com.ricardococati.layouts.TraillerBMFLayout;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceArrayPropertyEditor;

public class BMFCargaItemReader implements ItemReader<MultiResourceItemReader<FieldSet>>, ItemStream, Serializable {

	private static final long serialVersionUID = 1602197835137714641L;
	private final static String HEADER = "00*";
	private final static String DETALHE_SEGMENTO_G = "???????3?????G*";
	private final static String TRAILER = "???????9*";
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
	public MultiResourceItemReader<FieldSet> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

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

		HeaderBMFLayout headerLayout = new HeaderBMFLayout();
		tokenizers.put(HEADER, headerLayout.configurarParser());

		CotacoesDosPapeisPorDiaLayout detalheSegmentoGLayout = new CotacoesDosPapeisPorDiaLayout();
		tokenizers.put(DETALHE_SEGMENTO_G, detalheSegmentoGLayout.configurarParser());

		TraillerBMFLayout trailerLayout = new TraillerBMFLayout();
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

	}

	@Override
	public void update(ExecutionContext arg0) throws ItemStreamException {

	}

}
