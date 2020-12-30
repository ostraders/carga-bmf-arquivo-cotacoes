package com.ricardococati.carga.usecases.batchprocess.processor;

import static java.util.Objects.isNull;

import com.ricardococati.carga.utils.ConverteStringParaLocalDate;
import com.ricardococati.carga.config.ControleArquivoConfig;
import com.ricardococati.carga.entities.domains.arquivo.Arquivo;
import com.ricardococati.carga.entities.enums.TipoRegistroEnum;
import com.ricardococati.carga.entities.enums.TiposCamposEnum;
import com.ricardococati.carga.usecases.batchprocess.layouts.CotacoesDosPapeisPorDiaLayoutImpl;
import com.ricardococati.carga.usecases.batchprocess.layouts.HeaderBMFLayoutImpl;
import com.ricardococati.carga.usecases.batchprocess.layouts.TraillerBMFLayoutImpl;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BMFCargaValidaEstruturaArquivoProcessor implements ItemProcessor<FieldSet, Arquivo> {

  private HeaderBMFLayoutImpl header = null;
  private CotacoesDosPapeisPorDiaLayoutImpl cotacoes;
  private TraillerBMFLayoutImpl trailler;

  private final ControleArquivoConfig arquivoConfig;

  @Override
  public Arquivo process(FieldSet line) throws Exception {
    try {
      BMFUtil bmfUtil = new BMFUtil();
      bmfUtil.setTrackingID(UUID.randomUUID().toString());
      bmfUtil.setLine(line);
      bmfUtil.setTipoRegistro(line.readRawString("tipoRegistro"));
      validarArquivo(bmfUtil);
    } catch (Exception ex) {
      arquivoConfig.setArquivoValido(false);
      String mensagemErro = "OCORREU UM ERRO NA VALIDACAO DO ARQUIVO -  ControleProcessamentoErro - process";
      log.info(mensagemErro);
    }
    return null;
  }

  private void validarArquivo(BMFUtil bmfUtil) throws Exception {
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
    } catch (Exception e) {
      log.error("Erro na execução do método validarArquivo: " + e.getMessage());
      throw e;
    }
  }

  private void validarTipagemDosCampos(String nomeCampo, String valorCampo, BMFUtil bmfUtil)
      throws Exception {
    header = new HeaderBMFLayoutImpl();
    cotacoes = new CotacoesDosPapeisPorDiaLayoutImpl();
    trailler = new TraillerBMFLayoutImpl();

    try {

      TiposCamposEnum tipoCampo = null;

      if (bmfUtil.getTipoRegistro().equals(TipoRegistroEnum.HEADER.getCod())) {
        tipoCampo = header.getTipos().get(nomeCampo);
      } else if (TipoRegistroEnum.DETALHE.getCod().equals(bmfUtil.getTipoRegistro())) {
        tipoCampo = cotacoes.getTipos().get(nomeCampo);
      } else if (bmfUtil.getTipoRegistro().equals(TipoRegistroEnum.TRAILER.getCod())) {
        tipoCampo = trailler.getTipos().get(nomeCampo);
      }

      if (tipoCampo.equals(TiposCamposEnum.NUMERICO)) {
        new BigInteger(valorCampo.trim());
      }
      if(tipoCampo.equals(TiposCamposEnum.DATA)){
        LocalDate localDate = ConverteStringParaLocalDate.converte(valorCampo);
        if(isNull(localDate)){
          new Exception("Erro na conversão da data");
        }
      }

    } catch (Exception e) {
      arquivoConfig.setArquivoValido(false);
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
