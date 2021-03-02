package com.ricardococati.carga.usecases.batchprocess.validator;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VerificadorArquivoSkipper implements SkipPolicy, Serializable {

	private static final long serialVersionUID = 587789236177587832L;
	private static final String MENSAGEM_LINHA = "LINHA - ";
	private static final String MENSAGEM_ERRO = "Ocorreu um erro no metodo VerificadorArquivoSkipper.shouldSkip ";
	private static final String MENSAGEM_INFO = "ARQUIVO INVALIDO - shouldSkip ";

	@Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
			try {
				if(exception.getClass().equals(FlatFileParseException.class)) {
					FlatFileParseException flatFileParseException = (FlatFileParseException) exception;
					log.info(
							MENSAGEM_INFO +
							flatFileParseException.getMessage() +
							MENSAGEM_LINHA +
							flatFileParseException.getLineNumber()
					);
				}
			} catch (Exception e) {
				log.error(MENSAGEM_ERRO + e.getMessage());
			}
			return false;
    }
}