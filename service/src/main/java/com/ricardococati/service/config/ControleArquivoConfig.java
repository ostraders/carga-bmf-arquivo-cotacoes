package com.ricardococati.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@ComponentScan(basePackages = { "com.ricardococati.*" })
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class ControleArquivoConfig {

  private Boolean arquivoValido = true;

  @Value("${url.arquivo.cotacoes}")
  private String urlArquivoCotacoes;

}
