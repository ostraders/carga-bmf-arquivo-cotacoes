package com.ricardococati.processor;

import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.dto.DetalheSegmentoGDTO;
import com.ricardococati.dto.Header;
import com.ricardococati.enums.TipoRegistroEnum;
import com.ricardococati.util.Funcoes;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;

@Slf4j
public class BMFCargaItemProcessor implements ItemProcessor<FieldSet, BMFCargaDTO>, Serializable {

	private static final long serialVersionUID = 1602197886947938991L;

	@Override
	public BMFCargaDTO process(FieldSet line) throws Exception {
		log.info("Iniciando ItemProcess: " + line);

		String identificacao = line.readString("tipoRegistro");

		BMFCargaDTO bmfCargaDTO = null;
		if(identificacao.equals(TipoRegistroEnum.DETALHE.getCod())){
			identificacao = line.readString("codSegmento");
		}
		
		if(identificacao.equals(TipoRegistroEnum.HEADER.getCod())) {
			Header header = new Header();
			header.setTipoRegistro(line.readLong("tipoRegistro"));
			header.setNomeDoArquivo(line.readString("nomeDoArquivo"));
			header.setCodigoDaOrigem(line.readString("codigoDaOrigem"));
			header.setDataDaGeracaoDoArquivo(Funcoes.formatarDateReaderDate(line.readString("dataDaGeracaoDoArquivo")));
			header.setReserva(line.readString("reserva"));
			return bmfCargaDTO = header;
		}

		if (identificacao.equals(TipoRegistroEnum.DETALHE.getNome())) {
			DetalheSegmentoGDTO detalheSegmentoGDTO = new DetalheSegmentoGDTO();
			detalheSegmentoGDTO.setCodCompensacaoBco(line.readLong("codCompensacaoBco"));
			detalheSegmentoGDTO.setLoteServico(line.readLong("loteServico"));
			detalheSegmentoGDTO.setTipoRegistro(line.readLong("tipoRegistro"));
			detalheSegmentoGDTO.setNroSequencial(line.readLong("nroSequencial"));
			detalheSegmentoGDTO.setCodSegmento(line.readString("codSegmento"));
			detalheSegmentoGDTO.setFormaPgto(line.readString("formaPgto"));
			detalheSegmentoGDTO.setCodOcorrenciaBase(line.readLong("codOcorrenciaBase"));
			detalheSegmentoGDTO.setCodBarra(line.readString("codBarra"));
			detalheSegmentoGDTO.setTpoInscCedente(line.readLong("tpoInscCedente"));
			detalheSegmentoGDTO.setInscCedente(line.readLong("inscCedente"));
			detalheSegmentoGDTO.setNomeCedente(line.readString("nomeCedente"));
			detalheSegmentoGDTO.setDtaVencimento(Funcoes.formatarDateReaderDate(line.readString("dtaVencimento")));
			detalheSegmentoGDTO.setVlrTitulo(Funcoes.dividePorCem(line.readBigDecimal("vlrTitulo")));
			detalheSegmentoGDTO.setQtdMoeda(line.readLong("qtdMoeda"));
			detalheSegmentoGDTO.setCodMoeda(line.readLong("codMoeda"));
			detalheSegmentoGDTO.setNroDocCobranca(line.readString("nroDocCobranca"));
			detalheSegmentoGDTO.setCodAgCobranca(line.readLong("codAgCobranca"));
			detalheSegmentoGDTO.setDvAgencia(line.readString("dvAgencia"));
			detalheSegmentoGDTO.setPracaCobranca(line.readString("pracaCobranca"));
			detalheSegmentoGDTO.setCodCarteira(line.readString("codCarteira"));
			detalheSegmentoGDTO.setEspecieTitulo(line.readString("especieTitulo"));
			detalheSegmentoGDTO.setDtaEmissaoTitulo(Funcoes.formatarDateReaderDate(line.readString("dtaEmissaoTitulo")));
			detalheSegmentoGDTO.setJurosMoraPorDia(Funcoes.dividePorCem(line.readBigDecimal("jurosMoraPorDia")));
			detalheSegmentoGDTO.setCodDesconto(line.readLong("codDesconto"));
			detalheSegmentoGDTO.setDtaDesconto(Funcoes.formatarDateReaderDate(line.readString("dtaDesconto")));
			detalheSegmentoGDTO.setVlrDesconto(Funcoes.dividePorCem(line.readBigDecimal("vlrDesconto")));
			detalheSegmentoGDTO.setCodProtesto(line.readLong("codProtesto"));
			detalheSegmentoGDTO.setPrazoProtesto(line.readLong("prazoProtesto"));
			detalheSegmentoGDTO.setDtaLimitePagtoTitulo(Funcoes.formatarDateReaderDate(line.readString("dtaLimitePagtoTitulo")));
			detalheSegmentoGDTO.setOcorProcessamento(0L);
			bmfCargaDTO = detalheSegmentoGDTO;
		}

		return bmfCargaDTO;
	}

}
