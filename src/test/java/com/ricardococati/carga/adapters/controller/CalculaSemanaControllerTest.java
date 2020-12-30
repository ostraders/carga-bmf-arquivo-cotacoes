package com.ricardococati.carga.adapters.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ricardococati.carga.adapters.controllers.calcula.CalculaSemanaController;
import com.ricardococati.carga.usecases.candlestick.CalculaCandlestickSemanalAsyncService;
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
@WebMvcTest(controllers = {CalculaSemanaController.class})
@AutoConfigureMockMvc(addFilters = false)
public class CalculaSemanaControllerTest {

  @MockBean
  private CalculaCandlestickSemanalAsyncService service;
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void calcular() throws Exception {
    //when
    final ResultActions result = this.mockMvc
        .perform(
            get(String.format("/api/v1/calcular-semana"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    //then
    result
        .andExpect(status().isNoContent());
  }

  @Test
  public void calcularByData() throws Exception {
    //given
    String dtPregao = "16-02-1978";
    //when
    final ResultActions result = this.mockMvc
        .perform(
            get(String.format("/api/v1/calcular-semana/by-data"))
                .param("dtPregrao", dtPregao)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    //then
    result
        .andExpect(status().isNoContent());
  }

}
