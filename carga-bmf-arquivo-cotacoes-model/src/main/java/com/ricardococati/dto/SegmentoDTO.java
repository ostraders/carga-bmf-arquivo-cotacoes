package com.ricardococati.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SegmentoDTO implements Serializable {

  private static final long serialVersionUID = 635171180528520023L;
  private char tipoRegistro;
  private Long nroSegmento;
  private Date created;

}
