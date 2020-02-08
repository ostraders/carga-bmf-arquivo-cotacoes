package com.ricardococati.controller;

import com.ricardococati.model.response.BaixarArquivo;
import com.ricardococati.service.BaixarArquivoService;
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
@Api(value = "api/v1/baixar", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BaixarArquivoController {

  private final BaixarArquivoService arquivoService;

  @GetMapping
  public ResponseEntity<BaixarArquivo> baixarArquivo(
      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dtPregrao
  ) throws Exception {
    log.info("Excutando baixando Arquivo ");
    BaixarArquivo retorno = arquivoService.baixarArquivoCotacao(dtPregrao);
    log.info("Arquivo baixado: {} ", retorno);
    return ResponseEntity.ok().body(retorno);
  }

}
