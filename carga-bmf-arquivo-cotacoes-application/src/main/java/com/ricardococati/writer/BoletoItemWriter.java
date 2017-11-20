package com.ricardococati.writer;

import java.io.Serializable;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.ricardococati.dto.BoletoDTO;
import com.ricardococati.service.IBoletoService;

import lombok.Data;

@Data
public class BoletoItemWriter implements ItemWriter<BoletoDTO>, Serializable {

	private static final long serialVersionUID = 771329275101100444L;

	@Autowired
	private IBoletoService boletoService;
	
	@Override
	public void write(List<? extends BoletoDTO> listBoletoDTO) throws Exception {
		boletoService.insereDados(listBoletoDTO);
	}

}
