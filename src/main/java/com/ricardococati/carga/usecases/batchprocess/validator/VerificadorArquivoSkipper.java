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
	
	@Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
		try {
			String msg = "ARQUIVO INVALIDO - shouldSkip ";
			if(exception.getClass().equals(FlatFileParseException.class)) {
				FlatFileParseException flatFileParseException = (FlatFileParseException) exception;
				log.info(msg + flatFileParseException.getMessage().toString() + "LINHA - " + String.valueOf(flatFileParseException.getLineNumber()));
			}
		} catch (Exception e) {
			log.error("Ocorreu um erro no metodo VerificadorArquivoSkipper.shouldSkip " + e.getMessage());
		}
        return false;
    }
}