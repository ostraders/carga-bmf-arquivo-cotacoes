package com.ricardococati.service.batchprocess.writer;

import com.ricardococati.model.dto.BMFCargaDTO;
import com.ricardococati.model.dto.Cotacao;
import com.ricardococati.model.dto.Header;
import com.ricardococati.service.IBMFCargaCotacaoService;
import com.ricardococati.service.IBMFCargaHeaderService;
import com.ricardococati.service.config.ControleArquivoConfig;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
@RequiredArgsConstructor
public class BMFCargaItemWriter implements ItemWriter<BMFCargaDTO> {

	private final IBMFCargaCotacaoService cargaCotacaoService;
	private final IBMFCargaHeaderService cargaHeaderService;
	private final ControleArquivoConfig arquivoConfig;
	
	@Override
	public void write(List<? extends BMFCargaDTO> listDTOs) {
		try{
			listDTOs
					.stream()
					.filter(Objects::nonNull)
					.forEach(bmfCargaDTO -> {
						if (Header.class.isInstance(bmfCargaDTO)) {
							cargaHeaderService.insereDados((Header) bmfCargaDTO);
						} else if (Cotacao.class.isInstance(bmfCargaDTO)){
							final Cotacao cotacao = (Cotacao) bmfCargaDTO;
							cargaCotacaoService.insereDados(cotacao);
						}
					});
		} catch (Exception e) {
			arquivoConfig.setArquivoValido(false);
			log.error("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: {}" + e.getMessage());
		}
	}

}
