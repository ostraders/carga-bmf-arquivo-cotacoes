package com.ricardococati.controller;

import com.ricardococati.controller.converter.SplitInplitConverter;
import com.ricardococati.service.SplitInplitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/split-inplit")
@Api(value = "api/v1/split-inplit", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SplitInplitController {

  private final SplitInplitConverter converter;
  private final SplitInplitService service;

  @ApiOperation(value = "Split na acao por: codigo de negocio, data do pregão e quantidade de divisões")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "Split OK"),
          @ApiResponse(code = 400, message = "Bad Request"),
          @ApiResponse(code = 409, message = "Conflict Request"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/split")
  public ResponseEntity<?> split(
      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dtPregrao,
      @RequestParam String codneg,
      @RequestParam Integer qtdSplitInplit
  ) {
    log.info("Excutando Split ");
    service.split(converter.convert(dtPregrao, codneg, qtdSplitInplit));
    log.info("Split executado com sucesso!! ");
    return ResponseEntity.noContent().build();
  }

  @ApiOperation(value = "Inplit na acao por: codigo de negocio, data do pregão e quantidade de multiplicações")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "Inplit OK"),
          @ApiResponse(code = 400, message = "Bad Request"),
          @ApiResponse(code = 409, message = "Conflict Request"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/inplit")
  public ResponseEntity<?> inplit(
      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dtPregrao,
      @RequestParam String codneg,
      @RequestParam Integer qtdSplitInplit
  ) {
    log.info("Excutando Split ");
    service.inplit(converter.convert(dtPregrao, codneg, qtdSplitInplit));
    log.info("Inplit executado com sucesso!! ");
    return ResponseEntity.noContent().build();
  }

}
