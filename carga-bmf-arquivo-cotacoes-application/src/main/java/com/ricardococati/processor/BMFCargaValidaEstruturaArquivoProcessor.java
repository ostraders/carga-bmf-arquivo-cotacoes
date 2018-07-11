package com.ricardococati.processor;

import static java.util.Objects.isNull;

import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.enums.TipoRegistroEnum;
import com.ricardococati.enums.TiposCamposEnum;
import com.ricardococati.exception.LinhaInvalidaException;
import com.ricardococati.layouts.CotacoesDosPapeisPorDiaLayout;
import com.ricardococati.layouts.HeaderBMFLayout;
import com.ricardococati.layouts.TraillerBMFLayout;
import com.ricardococati.service.impl.IntegrationService;
import com.ricardococati.util.Funcoes;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BMFCargaValidaEstruturaArquivoProcessor implements
    ItemProcessor<FieldSet, BMFCargaDTO> {

  private HeaderBMFLayout header = null;
  private CotacoesDosPapeisPorDiaLayout cotacoes;
  private TraillerBMFLayout trailler;

  @Autowired
  private IntegrationService integrationService;

  @Autowired
  private ArquivoDTO arquivoDTO;

  @Override
  public BMFCargaDTO process(FieldSet line) throws Exception {
    try {
      BMFUtil bmfUtil = new BMFUtil();
      bmfUtil.setTrackingID(UUID.randomUUID().toString());
      bmfUtil.setLine(line);
      bmfUtil.setTipoRegistro(line.readRawString("tipoRegistro"));
      validarArquivo(bmfUtil);
    } catch (Exception ex) {
      integrationService.setArquivoValido(false);
      String mensagemErro = "OCORREU UM ERRO NA VALIDACAO DO ARQUIVO -  ControleProcessamentoErro - process";
      log.info(mensagemErro);
    }
    return null;
  }

  private void validarArquivo(BMFUtil bmfUtil) throws Exception, LinhaInvalidaException {
    try {
      String[] lineNameField = bmfUtil.getLine().getNames();
      String[] lineValueField = bmfUtil.getLine().getValues();

      String nameField = "";
      String valueField = "";

      for (int i = 0; i < lineValueField.length; i++) {
        nameField = lineNameField[i];
        valueField = lineValueField[i];
        validarTipagemDosCampos(nameField, valueField, bmfUtil);
      }
    } catch (LinhaInvalidaException e) {
      log.error("Erro na execução do método validarArquivo: " + e.getMessage());
      throw e;
    }
  }

  private void validarTipagemDosCampos(String nomeCampo, String valorCampo, BMFUtil bmfUtil)
      throws Exception, LinhaInvalidaException {
    header = new HeaderBMFLayout();
    cotacoes = new CotacoesDosPapeisPorDiaLayout();
    trailler = new TraillerBMFLayout();

    try {

      TiposCamposEnum tipoCampo = null;

      if (bmfUtil.getTipoRegistro().equals(TipoRegistroEnum.HEADER.getCod())) {
        tipoCampo = (TiposCamposEnum) header.getTipos().get(nomeCampo);
      } else if (TipoRegistroEnum.DETALHE.getCod().equals(bmfUtil.getTipoRegistro())) {
        tipoCampo = (TiposCamposEnum) cotacoes.getTipos().get(nomeCampo);
      } else if (bmfUtil.getTipoRegistro().equals(TipoRegistroEnum.TRAILER.getCod())) {
        tipoCampo = (TiposCamposEnum) trailler.getTipos().get(nomeCampo);
      }

      if (tipoCampo.equals(TiposCamposEnum.NUMERICO)) {
        new BigInteger(valorCampo.trim());
      }
      if(tipoCampo.equals(TiposCamposEnum.DATA)){
        LocalDate localDate = Funcoes.convertStringToLocalDate(valorCampo);
        if(isNull(localDate)){
          new Exception("Erro na conversão da data");
        }
      }

    } catch (Exception e) {
      integrationService.setArquivoValido(false);
      String mensagemErro =
          "Ocorreu um erro ao validar o " + "LAYOUT " + bmfUtil.getTipoRegistro() + " Campo "
              + nomeCampo + " Numerico : ";
      log.info(mensagemErro + e.toString());
    }

  }

  @Data
  private class BMFUtil {

    private String tipoRegistro;
    private String segmento;
    private FieldSet line;
    private int formPgto;
    private String codOcorr;
    private String trackingID;

  }
}
