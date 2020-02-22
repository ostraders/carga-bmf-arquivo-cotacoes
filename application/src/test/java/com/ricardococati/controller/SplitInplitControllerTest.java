package com.ricardococati.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ricardococati.controller.converter.SplitInplitConverter;
import com.ricardococati.service.AtualizarCandlesticksService;
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
@WebMvcTest(controllers = {SplitInplitController.class})
@AutoConfigureMockMvc(addFilters = false)
public class SplitInplitControllerTest {

  @MockBean
  private AtualizarCandlesticksService atualizarCandlestickDiarioService;
  @MockBean
  private SplitInplitConverter converter;
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void splitSucesso() throws Exception {
    //given
    String dtPregao = "16-02-1978";
    String codneg = "abc123";
    String qtdSplitInplit = "3";
    String operacao = "SPLIT";
    when(converter.convert(any(), any(), any(), any())).thenCallRealMethod();
    //when
    final ResultActions result = this.mockMvc
        .perform(
            put(String.format("/api/v1/split-inplit"))
                .param("dtPregrao", dtPregao)
                .param("codneg", codneg)
                .param("qtdSplitInplit", qtdSplitInplit)
                .param("operacao", operacao)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    //then
    result
        .andExpect(status().isOk());
  }

  @Test
  public void splitErrorParameter() throws Exception {
    //given
    String dtPregao = "1978-02-16";
    String codneg = null;
    String qtdSplitInplit = "d";
    String operacao = "SPLIT";
    //when
    final ResultActions result = this.mockMvc
        .perform(
            put(String.format("/api/v1/split-inplit/split"))
                .param("dtPregrao", dtPregao)
                .param("codneg", codneg)
                .param("qtdSplitInplit", qtdSplitInplit)
                .param("operacao", operacao)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    //then
    result
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void inplitSucesso() throws Exception {
    //given
    String dtPregao = "16-02-1978";
    String codneg = "abc123";
    String qtdSplitInplit = "3";
    String operacao = "INPLIT";
    when(converter.convert(any(), any(), any(), any())).thenCallRealMethod();
    //when
    final ResultActions result = this.mockMvc
        .perform(
            put(String.format("/api/v1/split-inplit"))
                .param("dtPregrao", dtPregao)
                .param("codneg", codneg)
                .param("qtdSplitInplit", qtdSplitInplit)
                .param("operacao", operacao)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    //then
    result
        .andExpect(status().isOk());
  }

  @Test
  public void inplitErrorParameter() throws Exception{
    //given
    String dtPregao = "1978-02-16";
    String codneg = null;
    String qtdSplitInplit = "d";
    String operacao = "INPLIT";
    //when
    final ResultActions result = this.mockMvc
        .perform(
            put(String.format("/api/v1/split-inplit/inplit"))
                .param("dtPregrao", dtPregao)
                .param("codneg", codneg)
                .param("qtdSplitInplit", qtdSplitInplit)
                .param("operacao", operacao)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    //then
    result
        .andExpect(status().is4xxClientError());
  }

}
