package com.ricardococati.service.converter;

import com.ricardococati.dto.Cotacao;
import com.ricardococati.dto.CotacaoDTO;
import com.ricardococati.dto.Header;
import com.ricardococati.dto.HeaderDTO;
import org.springframework.stereotype.Component;

@Component
public class CotacaoConverter {

  public CotacaoDTO convert(Cotacao cotacao){
    return CotacaoDTO
        .builder()
        .tipoRegistro(cotacao.getTipoRegistro())
        .codbdi(cotacao.getCodbdi())
        .codneg(cotacao.getCodneg())
        .codisi(cotacao.getCodisi())
        .datven(cotacao.getDatven())
        .dismes(cotacao.getDismes())
        .dtpreg(cotacao.getDtpreg())
        .especi(cotacao.getEspeci())
        .fatcot(cotacao.getFatcot())
        .indopc(cotacao.getIndopc())
        .modref(cotacao.getModref())
        .nomres(cotacao.getNomres())
        .prazot(cotacao.getPrazot())
        .preabe(cotacao.getPreabe())
        .preexe(cotacao.getPreexe())
        .premax(cotacao.getPremax())
        .premed(cotacao.getPremed())
        .premin(cotacao.getPremin())
        .preofc(cotacao.getPreofc())
        .preofv(cotacao.getPreofv())
        .preult(cotacao.getPreult())
        .ptoexe(cotacao.getPtoexe())
        .quatot(cotacao.getQuatot())
        .totneg(cotacao.getTotneg())
        .tpmerc(cotacao.getTpmerc())
        .voltot(cotacao.getVoltot())
        .build();
  }

}
