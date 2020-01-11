package com.ricardococati.controller;

import com.ricardococati.service.CalculaCandlestickSemanalAsyncService;
import com.ricardococati.service.CalculaCandlestickSemanalByDataService;
import com.ricardococati.service.config.ControleArquivoConfig;
import io.swagger.annotations.Api;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/calcular-semana")
@Api(value = "api/v1/calcular-semana", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CalculaSemanaController {

  private final ControleArquivoConfig config;
  private final CalculaCandlestickSemanalAsyncService service;
  private final CalculaCandlestickSemanalByDataService byDataService;

  @PutMapping
  public ResponseEntity<?> calcular(
      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dtPregrao
  ) throws Exception {
    log.info("Executando calculo semana ");
    service.execute(dtPregrao);
    log.info("Executando calculo semana executado ");
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/by-data")
  public ResponseEntity<?> calcularByData(
      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dtPregrao
  ) throws Exception {
    log.info("Executando calculo semana ");
    byDataService.execute(dtPregrao);
    log.info("Executando calculo semana executado ");
    return ResponseEntity.noContent().build();
  }

}
