package com.ricardococati.processor;

import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.dto.Cotacao;
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
	public BMFCargaDTO process(FieldSet line) {
		log.info("Iniciando ItemProcess: " + line);

		String identificacao = line.readString("tipoRegistro");

		BMFCargaDTO bmfCargaDTO = null;

		if(identificacao.equals(TipoRegistroEnum.HEADER.getCod())) {
			Header header = new Header();
			header.setTipoRegistro(line.readLong("tipoRegistro"));
			header.setNomeDoArquivo(line.readString("nomeDoArquivo"));
			header.setCodigoDaOrigem(line.readString("codigoDaOrigem"));
			header.setDataDaGeracaoDoArquivo(Funcoes.convertStringToLocalDate(line.readString("dataDaGeracaoDoArquivo")));
			header.setReserva(line.readString("reserva"));
			bmfCargaDTO = header;
		}

		if (identificacao.equals(TipoRegistroEnum.DETALHE.getCod())) {
			Cotacao cotacao = new Cotacao();
			cotacao.setTipoRegistro(line.readLong("tipoRegistro"));
			cotacao.setDtpreg(Funcoes.convertStringToLocalDate(line.readString("dtpreg")));
			cotacao.setCodbdi(line.readString("codbdi"));
			cotacao.setCodneg(line.readString("codneg"));
			cotacao.setTpmerc(line.readLong("tpmerc"));
			cotacao.setNomres(line.readString("nomres"));
			cotacao.setEspeci(line.readString("especi"));
			cotacao.setPrazot(line.readString("prazot"));
			cotacao.setModref(line.readString("modref"));
			cotacao.setPreabe(Funcoes.dividePorCem(line.readBigDecimal("preabe")));
			cotacao.setPremax(Funcoes.dividePorCem(line.readBigDecimal("premax")));
			cotacao.setPremin(Funcoes.dividePorCem(line.readBigDecimal("premin")));
			cotacao.setPremed(Funcoes.dividePorCem(line.readBigDecimal("premed")));
			cotacao.setPreult(Funcoes.dividePorCem(line.readBigDecimal("preult")));
			cotacao.setPreofc(Funcoes.dividePorCem(line.readBigDecimal("preofc")));
			cotacao.setPreofv(Funcoes.dividePorCem(line.readBigDecimal("preofv")));
			cotacao.setTotneg(line.readLong("totneg"));
			cotacao.setQuatot(line.readLong("quatot"));
			cotacao.setVoltot(Funcoes.dividePorCem(line.readBigDecimal("voltot")));
			cotacao.setPreexe(Funcoes.dividePorCem(line.readBigDecimal("preexe")));
			cotacao.setIndopc(line.readLong("indopc"));
			cotacao.setDatven(Funcoes.convertStringToLocalDate(line.readString("datven")));
			cotacao.setFatcot(line.readLong("fatcot"));
			cotacao.setPtoexe(Funcoes.dividePorCem(line.readBigDecimal("ptoexe")));
			cotacao.setCodisi(line.readString("codisi"));
			cotacao.setDismes(line.readLong("dismes"));
			bmfCargaDTO = cotacao;
		}

		return bmfCargaDTO;
	}

}
