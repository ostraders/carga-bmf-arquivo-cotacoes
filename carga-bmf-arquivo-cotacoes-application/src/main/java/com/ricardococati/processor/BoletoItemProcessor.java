package com.ricardococati.processor;

import java.io.Serializable;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;

import com.ricardococati.dto.DetalheSegmentoGDTO;
import com.ricardococati.dto.BoletoDTO;
import com.ricardococati.dto.HeaderDTO;
import com.ricardococati.dto.HeaderLoteDTO;
import com.ricardococati.enums.TipoRegistroEnum;
import com.ricardococati.util.Funcoes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoletoItemProcessor implements ItemProcessor<FieldSet, BoletoDTO>, Serializable {

	private static final long serialVersionUID = 5894537786947938991L;

	@Override
	public BoletoDTO process(FieldSet line) throws Exception {
		log.info("Iniciando ItemProcess: " + line);

		String identificacao = line.readString("tipoRegistro");

		BoletoDTO boletoDTO = null;
		if(identificacao.equals(TipoRegistroEnum.DETALHE_SEGMENTO_G.getCod())){
			identificacao = line.readString("codSegmento");
		}
		
		if(identificacao.equals(TipoRegistroEnum.HEADER.getCod())) {
			HeaderDTO headerDTO = new HeaderDTO();
			headerDTO.setNroBco(line.readLong("nroBco"));
			headerDTO.setLoteServico(line.readLong("loteServico"));
			headerDTO.setTipoRegistro(line.readLong("tipoRegistro"));
			headerDTO.setExclusivoCnab(line.readString("exclusivoCnab"));
			headerDTO.setTipoInscricaoEmpresa(line.readLong("tipoInscricaoEmpresa"));
			headerDTO.setNroInscricaoEmpresa(line.readLong("nroInscricaoEmpresa"));
			headerDTO.setNroConvenio(line.readString("nroConvenio"));
			headerDTO.setNroAgencia(line.readLong("nroAgencia"));
			headerDTO.setDvAgencia(line.readString("dvAgencia"));
			headerDTO.setNroContaCorrente(line.readLong("nroContaCorrente"));
			headerDTO.setDvConta(line.readString("dvConta"));
			headerDTO.setDvAgConta(line.readString("dvAgConta"));
			headerDTO.setNomeEmpresa(line.readString("nomeEmpresa"));
			headerDTO.setNomeBco(line.readString("nomeBco"));
			headerDTO.setExclusivoCnab2(line.readString("exclusivoCnab2"));
			headerDTO.setNroRemessaRet(line.readLong("nroRemessaRet"));
			headerDTO.setDataGeracaoArquivo(line.readString("dataGeracaoArquivo"));
			headerDTO.setHoraGeracaoArquivo(line.readLong("horaGeracaoArquivo"));
			headerDTO.setNroSequencialArquivo(line.readLong("nroSequencialArquivo"));
			headerDTO.setNroVersaoLayoutArquivo(line.readLong("nroVersaoLayoutArquivo"));
			headerDTO.setDensidadeGravacaoArquivo(line.readLong("densidadeGravacaoArquivo"));
			headerDTO.setReservadorBco(line.readString("reservadorBco"));
			headerDTO.setReservadoEmpresa(line.readString("reservadoEmpresa"));
			headerDTO.setExclusivoCnab3(line.readString("exclusivoCnab3"));
			return boletoDTO = headerDTO;
		}

		if (identificacao.equals(TipoRegistroEnum.HEADER_LOTE.getCod())) {
			HeaderLoteDTO headerLoteDTO = new HeaderLoteDTO();
			headerLoteDTO.setCodBcoCompensacao(line.readLong("codBcoCompensacao"));
			headerLoteDTO.setLoteServico(line.readLong("loteServico"));
			headerLoteDTO.setTipoRegistro(line.readLong("tipoRegistro"));
			headerLoteDTO.setTipoOperacao(line.readString("tipoOperacao"));
			headerLoteDTO.setTipoServico(line.readLong("tipoServico"));
			headerLoteDTO.setExclusivoCnab(line.readString("exclusivoCnab"));
			headerLoteDTO.setNroVersaoLayoutLote(line.readLong("nroVersaoLayoutLote"));
			headerLoteDTO.setExclusivoCnab2(line.readString("exclusivoCnab2"));
			headerLoteDTO.setTipoInscricaoEmpresa(line.readLong("tipoInscricaoEmpresa"));
			headerLoteDTO.setNroInscricaoEmpresa(line.readLong("nroInscricaoEmpresa"));
			headerLoteDTO.setCodConvenioBco(line.readString("codConvenioBco"));
			headerLoteDTO.setAgenciaMantenedoraConta(line.readLong("agenciaMantenedoraConta"));
			headerLoteDTO.setDvAgencia(line.readString("dvAgencia"));
			headerLoteDTO.setNroContaCorrente(line.readLong("nroContaCorrente"));
			headerLoteDTO.setDvConta(line.readString("dvConta"));
			headerLoteDTO.setDvAgConta(line.readString("dvAgConta"));
			headerLoteDTO.setNomeEmpresa(line.readString("nomeEmpresa"));
			headerLoteDTO.setExclusivoCnab3(line.readString("exclusivoCnab3"));
			return boletoDTO = headerLoteDTO;
		}

		if (identificacao.equals(TipoRegistroEnum.DETALHE_SEGMENTO_G.getNome())) {
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
			detalheSegmentoGDTO.setDtaVencimento(Funcoes.formatarDateUtilForReaderDate(line.readString("dtaVencimento")));
			detalheSegmentoGDTO.setVlrTitulo(Funcoes.dividePorCem(line.readBigDecimal("vlrTitulo")));
			detalheSegmentoGDTO.setQtdMoeda(line.readLong("qtdMoeda"));
			detalheSegmentoGDTO.setCodMoeda(line.readLong("codMoeda"));
			detalheSegmentoGDTO.setNroDocCobranca(line.readString("nroDocCobranca"));
			detalheSegmentoGDTO.setCodAgCobranca(line.readLong("codAgCobranca"));
			detalheSegmentoGDTO.setDvAgencia(line.readString("dvAgencia"));
			detalheSegmentoGDTO.setPracaCobranca(line.readString("pracaCobranca"));
			detalheSegmentoGDTO.setCodCarteira(line.readString("codCarteira"));
			detalheSegmentoGDTO.setEspecieTitulo(line.readString("especieTitulo"));
			detalheSegmentoGDTO.setDtaEmissaoTitulo(Funcoes.formatarDateUtilForReaderDate(line.readString("dtaEmissaoTitulo")));
			detalheSegmentoGDTO.setJurosMoraPorDia(Funcoes.dividePorCem(line.readBigDecimal("jurosMoraPorDia")));
			detalheSegmentoGDTO.setCodDesconto(line.readLong("codDesconto"));
			detalheSegmentoGDTO.setDtaDesconto(Funcoes.formatarDateUtilForReaderDate(line.readString("dtaDesconto")));
			detalheSegmentoGDTO.setVlrDesconto(Funcoes.dividePorCem(line.readBigDecimal("vlrDesconto")));
			detalheSegmentoGDTO.setCodProtesto(line.readLong("codProtesto"));
			detalheSegmentoGDTO.setPrazoProtesto(line.readLong("prazoProtesto"));
			detalheSegmentoGDTO.setDtaLimitePagtoTitulo(Funcoes.formatarDateUtilForReaderDate(line.readString("dtaLimitePagtoTitulo")));
			detalheSegmentoGDTO.setOcorProcessamento(0L);
			boletoDTO = detalheSegmentoGDTO;
		}

		return boletoDTO;
	}

}
