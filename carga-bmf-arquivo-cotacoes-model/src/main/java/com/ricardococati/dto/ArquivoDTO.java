package com.ricardococati.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ArquivoDTO implements Serializable {

  private static final long serialVersionUID = 6838383473990652201L;
  private String nomeArquivo;
  private Long tamanhoArquivo;
  private String trackingID;
  private String sender;
  private String receiver;
  private String doctype;
  private Long codEmpFornax;

}
