package com.ricardococati.carga.usecases.batchprocess.layouts;

import java.io.Serializable;
import org.springframework.batch.item.file.transform.LineTokenizer;

public interface LayoutArquivo extends Serializable{
	
	LineTokenizer configurarParser();

}
