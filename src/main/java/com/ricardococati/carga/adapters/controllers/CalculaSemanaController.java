package com.ricardococati.carga.adapters.controllers;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.ricardococati.carga.usecases.CalculaCandlestickSemanalAsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/calcular-semana")
@Api(value = "api/v1/calcular-semana", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CalculaSemanaController {

  private final CalculaCandlestickSemanalAsyncService service;

  @ApiOperation(value = "Efetua todos os cálculos(Semanais)")
  @ApiResponses(
      value = {
          @ApiResponse(code = 204, message = "NO_CONTENT"),
          @ApiResponse(code = 400, message = "Bad Request"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  @ResponseStatus(NO_CONTENT)
  @GetMapping
  public ResponseEntity<?> calcular() throws Exception {
    log.info("Executando calculo semana ");
    service.execute();
    log.info("Inciada a thread para calculo da semana ");
    return ResponseEntity.noContent().build();
  }

  @ApiOperation(value = "Efetua todos os cálculos(Semanais) por data")
  @ApiResponses(
      value = {
          @ApiResponse(code = 204, message = "NO_CONTENT"),
          @ApiResponse(code = 400, message = "Bad Request"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  @ResponseStatus(NO_CONTENT)
  @GetMapping("/by-data")
  public ResponseEntity<?> calcularByData(
      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dtPregrao
  ) throws Exception {
    log.info("Executando calculo semana ");
    service.executeByData(dtPregrao);
    log.info("Inciada a thread para calculo da semana ");
    return ResponseEntity.noContent().build();
  }

}
