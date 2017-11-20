package com.ricardococati.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardococati.dao.GenericDAO;
import com.ricardococati.dao.IArquivoDAO;
import com.ricardococati.dao.IBoletoDAO;
import com.ricardococati.dao.IHeaderDAO;
import com.ricardococati.dao.ISegmentoDAO;
import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.BoletoDTO;
import com.ricardococati.dto.DetalheSegmentoGDTO;
import com.ricardococati.dto.HeaderDTO;
import com.ricardococati.service.IBoletoService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Service
public class BoletoService implements IBoletoService {
	
	@Autowired
	private IBoletoDAO boletoADAO;
	
	@Autowired
	private IHeaderDAO headerDAO;
	
	@Autowired
	private ISegmentoDAO segmentoDAO;
	
	@Autowired
	private IArquivoDAO arquivoDAO;
	
	@Autowired
	private GenericDAO genericDAO;
	
	@Autowired
	private ArquivoDTO arquivoDTO;
	
	@Autowired
	private IntegrationService integrationService;
	
	private Long codBoleto;

	@Override
	public void insereDados(List<? extends BoletoDTO> listBoletoDTO) {
		Long nroSegmento = 0L;
		try {
			if(listBoletoDTO != null && listBoletoDTO.size() > 0) {
				arquivoDAO.buscaDadosIntercambio(arquivoDTO);
				nroSegmento = segmentoDAO.incluirSegmento();
				codBoleto = 0L;
				for (BoletoDTO boletoDTO : listBoletoDTO) {
					if(HeaderDTO.class.isInstance(boletoDTO)) {
						HeaderDTO headerDTO = (HeaderDTO) boletoDTO;
						headerDAO.incluirHeaderArquivo(headerDTO, arquivoDTO);
					} else if(DetalheSegmentoGDTO.class.isInstance(boletoDTO)) {
						codBoleto = genericDAO.obterSequenceLong("SEGMENTOG_SEQ");
						DetalheSegmentoGDTO detalheSegmentoGDTO = (DetalheSegmentoGDTO) boletoDTO;
						detalheSegmentoGDTO.setCodSegmento(String.valueOf(nroSegmento));
						detalheSegmentoGDTO.setCodBoleto(codBoleto);
						boletoADAO.incluirBoleto(detalheSegmentoGDTO, arquivoDTO);
					}
				}
			}
		} catch (Exception e) {
			integrationService.setArquivoValido(false);
			log.info("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: " + e.getMessage());
		}
	}

}
