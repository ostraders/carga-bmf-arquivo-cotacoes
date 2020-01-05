package com.ricardococati.controller;

import com.ricardococati.service.BaixarArquivoService;
import com.ricardococati.service.config.ControleArquivoConfig;
import io.swagger.annotations.Api;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/baixar")
@Api(value = "api/v1/split-inplit", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BaixarArquivoController {

  private final ControleArquivoConfig config;
  private final BaixarArquivoService arquivoService;

  @GetMapping
  public ResponseEntity<?> baixarArquivo(
      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dtPregrao
  ) throws Exception {
    log.info("Excutando baixando Arquivo ");
    arquivoService.baixarArquivoCotacao(dtPregrao);
    return ResponseEntity.ok().body(config.getUrlArquivoCotacoes());
  }

}
