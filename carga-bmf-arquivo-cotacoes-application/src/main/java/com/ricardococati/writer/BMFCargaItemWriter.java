package com.ricardococati.writer;

import java.io.Serializable;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.service.IBMFCargaService;

import lombok.Data;

@Data
public class BMFCargaItemWriter implements ItemWriter<BMFCargaDTO>, Serializable {

	private static final long serialVersionUID = 771329275101100444L;

	@Autowired
	private IBMFCargaService boletoService;
	
	@Override
	public void write(List<? extends BMFCargaDTO> listBoletoDTO) throws Exception {
		boletoService.insereDados(listBoletoDTO);
	}

}
