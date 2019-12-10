package com.ricardococati.service.batchprocess.writer;

import com.ricardococati.model.dto.BMFCargaDTO;
import com.ricardococati.service.IBMFCargaService;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class BMFCargaItemWriter implements ItemWriter<BMFCargaDTO>, Serializable {

	private static final long serialVersionUID = 771329275101100444L;

	private final IBMFCargaService cargaService;
	
	@Override
	public void write(List<? extends BMFCargaDTO> bmfCargaDTOS) throws Exception {
		cargaService.insereDados(bmfCargaDTOS);
	}

}
