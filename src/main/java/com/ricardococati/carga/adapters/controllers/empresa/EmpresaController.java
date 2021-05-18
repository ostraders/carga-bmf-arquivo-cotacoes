package com.ricardococati.carga.adapters.controllers.empresa;

import com.ricardococati.carga.entities.domains.empresa.EmpresaRequest;
import com.ricardococati.carga.usecases.empresa.InserirEmpresa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/empresa")
@Api(value = "api/v1/empresa", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmpresaController {

  private final InserirEmpresa inserir;

  @ApiOperation(value = "Efetua criação de empresas")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "No content"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
      })
  @PostMapping
  public ResponseEntity<?> criaEmpresas(
      @RequestBody final EmpresaRequest empresaRequest
  ) throws Exception {
    log.info("Executando criação de empresas!");
    inserir.salvar(empresaRequest.getEmpresas());
    log.info("Criação executada com sucesso!");
    return ResponseEntity.noContent().build();
  }

}