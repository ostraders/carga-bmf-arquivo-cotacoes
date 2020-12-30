package com.ricardococati.carga.usecases.batchprocess.processor;

import com.ricardococati.carga.utils.ConverteStringParaLocalDate;
import com.ricardococati.carga.utils.ConverteValorDividindoPorCem;
import com.ricardococati.carga.entities.domains.arquivo.Arquivo;
import com.ricardococati.carga.entities.domains.cotacao.Cotacao;
import com.ricardococati.carga.entities.domains.header.Header;
import com.ricardococati.carga.entities.enums.TipoRegistroEnum;
import java.io.Serializable;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BMFCargaItemProcessor implements ItemProcessor<FieldSet, Arquivo>, Serializable {

	private static final long serialVersionUID = 1602197886947938991L;

	@Override
	public Arquivo process(FieldSet line) {
		String identificacao = line.readString("tipoRegistro");
		Arquivo arquivo = null;
		if(identificacao.equals(TipoRegistroEnum.HEADER.getCod())) {
			arquivo = Header
					.builder()
					.tipoRegistro(line.readLong("tipoRegistro"))
					.nomeDoArquivo(line.readString("nomeDoArquivo"))
					.codigoDaOrigem(line.readString("codigoDaOrigem"))
					.dataDaGeracaoDoArquivo(ConverteStringParaLocalDate.converte(line.readString("dataDaGeracaoDoArquivo")))
					.reserva(line.readString("reserva"))
					.build();
		}

		if (identificacao.equals(TipoRegistroEnum.DETALHE.getCod())) {
			Cotacao cotacao = new Cotacao();
			cotacao.setId(UUID.randomUUID().toString());
			cotacao.setTipoRegistro(line.readLong("tipoRegistro"));
			cotacao.setDtpreg(ConverteStringParaLocalDate.converte(line.readString("dtpreg")));
			cotacao.setCodbdi(line.readString("codbdi"));
			cotacao.setCodneg(line.readString("codneg"));
			cotacao.setTpmerc(line.readLong("tpmerc"));
			cotacao.setNomres(line.readString("nomres"));
			cotacao.setEspeci(line.readString("especi"));
			cotacao.setPrazot(line.readString("prazot"));
			cotacao.setModref(line.readString("modref"));
			cotacao.setPreabe(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("preabe")));
			cotacao.setPremax(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("premax")));
			cotacao.setPremin(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("premin")));
			cotacao.setPremed(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("premed")));
			cotacao.setPreult(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("preult")));
			cotacao.setPreofc(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("preofc")));
			cotacao.setPreofv(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("preofv")));
			cotacao.setTotneg(line.readLong("totneg"));
			cotacao.setQuatot(line.readLong("quatot"));
			cotacao.setVoltot(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("voltot")));
			cotacao.setPreexe(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("preexe")));
			cotacao.setIndopc(line.readLong("indopc"));
			cotacao.setDatven(ConverteStringParaLocalDate.converte(line.readString("datven")));
			cotacao.setFatcot(line.readLong("fatcot"));
			cotacao.setPtoexe(ConverteValorDividindoPorCem.divisao(line.readBigDecimal("ptoexe")));
			cotacao.setCodisi(line.readString("codisi"));
			cotacao.setDismes(line.readLong("dismes"));
			arquivo = cotacao;
		}

		return arquivo;
	}

}
