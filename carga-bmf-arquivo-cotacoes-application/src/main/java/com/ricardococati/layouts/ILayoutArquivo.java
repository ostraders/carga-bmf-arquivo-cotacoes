package com.ricardococati.layouts;

import java.io.Serializable;
import org.springframework.batch.item.file.transform.LineTokenizer;

public interface ILayoutArquivo extends Serializable{
	
	public LineTokenizer configurarParser();

}
