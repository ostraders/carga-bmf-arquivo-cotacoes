package com.ricardococati.read;

import com.ricardococati.enums.CaminhoArquivoEnum;
import com.ricardococati.layouts.HeaderLayout;
import com.ricardococati.layouts.HeaderLoteLayout;
import com.ricardococati.layouts.DetalheSegmentoGLayout;
import com.ricardococati.layouts.TrailerLoteLayout;
import com.ricardococati.layouts.TrailerLayout;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceArrayPropertyEditor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BoletoItemReader implements ItemReader<MultiResourceItemReader<FieldSet>>, ItemStream, Serializable {

	private static final long serialVersionUID = 1927411035137714641L;
	private final static String HEADER = "???????0*";
	private final static String HEADER_LOTE = "???????1*";
	private final static String DETALHE_SEGMENTO_G = "???????3?????G*";
	private final static String TRAILER_LOTE = "???????5*";
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

		HeaderLayout headerLayout = new HeaderLayout();
		tokenizers.put(HEADER, headerLayout.configurarParser());

		HeaderLoteLayout headerLoteLayout = new HeaderLoteLayout();
		tokenizers.put(HEADER_LOTE, headerLoteLayout.configurarParser());

		DetalheSegmentoGLayout detalheSegmentoGLayout = new DetalheSegmentoGLayout();
		tokenizers.put(DETALHE_SEGMENTO_G, detalheSegmentoGLayout.configurarParser());

		TrailerLoteLayout trailerLoteLayout = new TrailerLoteLayout();
		tokenizers.put(TRAILER_LOTE, trailerLoteLayout.configurarParser());

		TrailerLayout trailerLayout = new TrailerLayout();
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
