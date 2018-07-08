package com.ricardococati.processor;

import java.math.BigInteger;
import java.text.MessageFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;

import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.BMFCargaDTO;
import com.ricardococati.enums.TipoRegistroEnum;
import com.ricardococati.enums.TiposCamposEnum;
import com.ricardococati.exception.LinhaInvalidaException;
import com.ricardococati.layouts.DetalheSegmentoGLayout;
import com.ricardococati.layouts.HeaderLayout;
import com.ricardococati.layouts.HeaderLoteLayout;
import com.ricardococati.layouts.TrailerLayout;
import com.ricardococati.layouts.TrailerLoteLayout;
import com.ricardococati.service.impl.IntegrationService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BMFCargaValidaEstruturaArquivoProcessor implements
    ItemProcessor<FieldSet, BMFCargaDTO> {

  private HeaderLayout header = null;
  private HeaderLoteLayout headerLote = null;
  private DetalheSegmentoGLayout segmentoA;
  private TrailerLoteLayout traillerLote;
  private TrailerLayout trailler;
  private static final String TIPO_REGISTRO_DETALHE = "3";

  @Autowired
  private IntegrationService integrationService;

  @Autowired
  private ArquivoDTO arquivoDTO;

  @Override
  public BMFCargaDTO process(FieldSet line) throws Exception {
    try {
      BoletoUtil boletoUtil = new BoletoUtil();
      boletoUtil.setTrackingID(arquivoDTO.getTrackingID());
      boletoUtil.setLine(line);
      boletoUtil.setTipoRegistro(line.readRawString("tipoRegistro"));
      if (TIPO_REGISTRO_DETALHE.equals(boletoUtil.getTipoRegistro())) {
        boletoUtil.setSegmento(line.readRawString("codSegmento"));
      }
      validarArquivo(boletoUtil);
    } catch (LinhaInvalidaException ex) {
      integrationService.setArquivoValido(false);
      String mensagemErro = "OCORREU UM ERRO NA VALIDACAO DO ARQUIVO -  ControleProcessamentoErro - process";
      log.info(mensagemErro);
    }
    return null;
  }

  private void validarArquivo(BoletoUtil boletoUtil) throws Exception, LinhaInvalidaException {
    try {
      String[] lineNameField = boletoUtil.getLine().getNames();
      String[] lineValueField = boletoUtil.getLine().getValues();

      String nameField = "";
      String valueField = "";

      for (int i = 0; i < lineValueField.length; i++) {
        nameField = lineNameField[i];
        valueField = lineValueField[i];
        validarTipagemDosCampos(nameField, valueField, boletoUtil);
      }
    } catch (LinhaInvalidaException e) {
      log.error("Erro na execução do método validarArquivo: " + e.getMessage());
      throw e;
    }
  }

  private void validarTipagemDosCampos(String nomeCampo, String valorCampo, BoletoUtil boletoUtil)
      throws Exception, LinhaInvalidaException {
    header = new HeaderLayout();
    headerLote = new HeaderLoteLayout();
    segmentoA = new DetalheSegmentoGLayout();
    traillerLote = new TrailerLoteLayout();
    trailler = new TrailerLayout();

    try {

      TiposCamposEnum tipoCampo = null;

      if (boletoUtil.getTipoRegistro().equals(TipoRegistroEnum.HEADER.getCod())) {
        tipoCampo = (TiposCamposEnum) header.getTipos().get(nomeCampo);
      } else if (boletoUtil.getTipoRegistro().equals(TipoRegistroEnum.HEADER_LOTE.getCod())) {
        tipoCampo = (TiposCamposEnum) headerLote.getTipos().get(nomeCampo);
      } else if (TipoRegistroEnum.DETALHE_SEGMENTO_G.getCod().equals(boletoUtil.getTipoRegistro())
          &&
          TipoRegistroEnum.DETALHE_SEGMENTO_G.getNome().equals(boletoUtil.getSegmento())) {
        if ("codOcorrenciaBase".equals(nomeCampo)) {
          boletoUtil.setCodOcorr(boletoUtil.getLine().readRawString("codOcorrenciaBase"));
          boletoUtil.validaInclusaoExclusaoBoleto(boletoUtil);
        }
        if ("formaPgto".equals(nomeCampo)) {
          boletoUtil.setFormPgto(Integer.parseInt(boletoUtil.getLine().readRawString("formaPgto")));
          boletoUtil.validaTipoPagtoBoleto(boletoUtil);
        }
        tipoCampo = (TiposCamposEnum) segmentoA.getTipos().get(nomeCampo);
      } else if (boletoUtil.getTipoRegistro().equals(TipoRegistroEnum.TRAILER_LOTE.getCod())) {
        tipoCampo = (TiposCamposEnum) traillerLote.getTipos().get(nomeCampo);
      } else if (boletoUtil.getTipoRegistro().equals(TipoRegistroEnum.TRAILER.getCod())) {
        tipoCampo = (TiposCamposEnum) trailler.getTipos().get(nomeCampo);
      }

      if (tipoCampo.equals(TiposCamposEnum.NUMERICO)) {
        new BigInteger(valorCampo.trim());
      }

    } catch (LinhaInvalidaException e) {
      integrationService.setArquivoValido(false);
      String mensagemErro =
          "Ocorreu um erro ao validar o " + "LAYOUT " + boletoUtil.getTipoRegistro() + " Campo "
              + nomeCampo + " Numerico : ";
      log.info(mensagemErro + e.toString());
    }

  }

  @Data
  private class BoletoUtil {

    private String tipoRegistro;
    private String segmento;
    private FieldSet line;
    private int formPgto;
    private String codOcorr;
    private String trackingID;
    public static final int BOLETO_COBRANCA = 0;
    public static final String INCLUSAO = "0";
    public static final String EXCLUSAO = "9";
    public static final String INCLUSAO_SUCESSO = "1";
    public static final String INCLUSAO_RECUSADA = "2";
    public static final String EXCLUSAO_SUCESSO = "3";
    public static final String EXCLUSAO_RECUSADA = "4";
    public static final String E007 = "Forma de pagamento inválido. TrackingID: {0}.";
    public static final String E008 = "Código de ocorrência inválido. TrackingID: {0}";

    public void validaInclusaoExclusaoBoleto(BoletoUtil boletoUtil)
        throws Exception, LinhaInvalidaException {
      if (!INCLUSAO.equalsIgnoreCase(boletoUtil.getCodOcorr())
          && !EXCLUSAO.equalsIgnoreCase(boletoUtil.getCodOcorr())
          && !INCLUSAO_SUCESSO.equalsIgnoreCase(boletoUtil.getCodOcorr())
          && !INCLUSAO_RECUSADA.equalsIgnoreCase(boletoUtil.getCodOcorr())
          && !EXCLUSAO_SUCESSO.equalsIgnoreCase(boletoUtil.getCodOcorr())
          && !EXCLUSAO_RECUSADA.equalsIgnoreCase(boletoUtil.getCodOcorr())) {
        MessageFormat msgFormat = new MessageFormat(E008);
        String msg = msgFormat.format(new Object[]{boletoUtil.getTrackingID()});
        throw new LinhaInvalidaException(E008, msg);
      }
    }

    public void validaTipoPagtoBoleto(BoletoUtil boletoUtil)
        throws Exception, LinhaInvalidaException {
      if (BOLETO_COBRANCA != boletoUtil.getFormPgto()) {
        MessageFormat msgFormat = new MessageFormat(E007);
        String msg = msgFormat.format(new Object[]{boletoUtil.getTrackingID()});
        throw new LinhaInvalidaException(E007, msg);
      }
    }
  }
}
