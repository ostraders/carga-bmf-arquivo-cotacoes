package com.ricardococati.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ricardococati.model.response.BaixarArquivo;
import com.ricardococati.service.BaixarArquivoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {BaixarArquivoController.class})
@AutoConfigureMockMvc(addFilters = false)
public class BaixarArquivoControllerTest {

  @MockBean
  private BaixarArquivoService service;
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void baixarArquivo() throws Exception {
    //given
    final BaixarArquivo arquivo = buildBaixarArquivo();
    when(service.baixarArquivoCotacao(any())).thenReturn(arquivo);
    String dtPregao = "16-02-1978";
    //when
    final ResultActions result = this.mockMvc
        .perform(
            get(String.format("/api/v1/baixar"))
                .param("dtPregrao", dtPregao)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    //then
    result
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.url", is(arquivo.getUrl())))
        .andExpect(jsonPath("$.caminhoArquivoLocal", is(arquivo.getCaminhoArquivoLocal())));
  }

  @Test
  public void baixarArquivoNull() throws Exception {
    //given
    when(service.baixarArquivoCotacao(any())).thenReturn(null);
    String dtPregao = "16-02-1978";
    //when
    final ResultActions result = this.mockMvc
        .perform(
            get(String.format("/api/v1/baixar"))
                .param("dtPregrao", dtPregao)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    //then
    result
        .andExpect(status().isOk());
  }

  private BaixarArquivo buildBaixarArquivo(){
    return BaixarArquivo
        .builder()
        .url("http://bvmf.bmfbovespa.com.br/InstDados/SerHist/COTAHIST_D20200216.ZIP")
        .caminhoArquivoLocal("/data/bmfCarga/zip/COTAHIST_D02012020.zip")
        .build();
  }

}
