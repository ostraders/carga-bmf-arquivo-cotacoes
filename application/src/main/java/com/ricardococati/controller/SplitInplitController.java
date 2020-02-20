package com.ricardococati.controller;

import com.ricardococati.controller.converter.SplitInplitConverter;
import com.ricardococati.model.entities.SplitInplit;
import com.ricardococati.service.AtualizarCandlesticksService;
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
  private final AtualizarCandlesticksService service;

  @ApiOperation(
      value = "Split | Inplit na acao por: "
          + "codigo de negocio(String), "
          + "data do pregão(DD/MM/YYYY), "
          + "quantidade de divisões e(Integer) "
          + "operação(SPLIT ou INPLIT)")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "Split OK"),
          @ApiResponse(code = 400, message = "Bad Request"),
          @ApiResponse(code = 409, message = "Conflict Request"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  @ResponseStatus(HttpStatus.OK)
  @PutMapping
  public ResponseEntity<?> splitInplit(
      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dtPregrao,
      @RequestParam String codneg,
      @RequestParam Integer qtdSplitInplit,
      @RequestParam String operacao
  ) throws Exception {
    log.info("Excutando Split ");
    final SplitInplit splitInplit =
        converter.convert(dtPregrao, codneg, qtdSplitInplit, operacao);
    log.info("Objeto convertido: {}", splitInplit);
    service.executeSplitInplit(splitInplit);
    log.info("Split executado com sucesso!! ");
    return ResponseEntity.ok().build();
  }

}
