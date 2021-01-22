package com.ricardococati.carga.usecases.batchprocess.processor;

import static java.util.Objects.isNull;

import com.ricardococati.carga.utils.ConverteStringParaLocalDate;
import com.ricardococati.carga.config.ControleArquivoConfig;
import com.ricardococati.carga.entities.domains.arquivo.Arquivo;
import com.ricardococati.carga.entities.enums.TipoRegistroEnum;
import com.ricardococati.carga.entities.enums.TiposCamposEnum;
import com.ricardococati.carga.usecases.batchprocess.layouts.CotacaoLayoutImpl;
import com.ricardococati.carga.usecases.batchprocess.layouts.HeaderLayoutImpl;
import com.ricardococati.carga.usecases.batchprocess.layouts.TraillerLayoutImpl;
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
public class CargaValidaEstruturaArquivoProcessor implements ItemProcessor<FieldSet, Arquivo> {

  private HeaderLayoutImpl header = null;
  private CotacaoLayoutImpl cotacoes;
  private TraillerLayoutImpl trailler;

  private final ControleArquivoConfig arquivoConfig;

  @Override
  public Arquivo process(FieldSet line) throws Exception {
    try {
      ValidaUtil validaUtil = new ValidaUtil();
      validaUtil.setTrackingID(UUID.randomUUID().toString());
      validaUtil.setLine(line);
      validaUtil.setTipoRegistro(line.readRawString("tipoRegistro"));
      validarArquivo(validaUtil);
    } catch (Exception ex) {
      arquivoConfig.setArquivoValido(false);
      String mensagemErro = "OCORREU UM ERRO NA VALIDACAO DO ARQUIVO -  ControleProcessamentoErro - process";
      log.info(mensagemErro);
    }
    return null;
  }

  private void validarArquivo(ValidaUtil validaUtil) throws Exception {
    try {
      String[] lineNameField = validaUtil.getLine().getNames();
      String[] lineValueField = validaUtil.getLine().getValues();

      String nameField = "";
      String valueField = "";

      for (int i = 0; i < lineValueField.length; i++) {
        nameField = lineNameField[i];
        valueField = lineValueField[i];
        validarTipagemDosCampos(nameField, valueField, validaUtil);
      }
    } catch (Exception e) {
      log.error("Erro na execução do método validarArquivo: " + e.getMessage());
      throw e;
    }
  }

  private void validarTipagemDosCampos(String nomeCampo, String valorCampo, ValidaUtil validaUtil)
      throws Exception {
    header = new HeaderLayoutImpl();
    cotacoes = new CotacaoLayoutImpl();
    trailler = new TraillerLayoutImpl();

    try {

      TiposCamposEnum tipoCampo = null;

      if (validaUtil.getTipoRegistro().equals(TipoRegistroEnum.HEADER.getCod())) {
        tipoCampo = header.getTipos().get(nomeCampo);
      } else if (TipoRegistroEnum.DETALHE.getCod().equals(validaUtil.getTipoRegistro())) {
        tipoCampo = cotacoes.getTipos().get(nomeCampo);
      } else if (validaUtil.getTipoRegistro().equals(TipoRegistroEnum.TRAILER.getCod())) {
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
          "Ocorreu um erro ao validar o " + "LAYOUT " + validaUtil.getTipoRegistro() + " Campo "
              + nomeCampo + " Numerico : ";
      log.info(mensagemErro + e.toString());
    }

  }

  @Data
  private class ValidaUtil {

    private String tipoRegistro;
    private String segmento;
    private FieldSet line;
    private int formPgto;
    private String codOcorr;
    private String trackingID;

  }
}
