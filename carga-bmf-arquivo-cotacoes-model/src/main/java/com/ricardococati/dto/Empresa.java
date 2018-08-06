package com.ricardococati.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "empresa")
public class Empresa {

  @Id
  private String codneg;

}
