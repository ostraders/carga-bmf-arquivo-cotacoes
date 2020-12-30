package com.ricardococati.carga.util;

import static com.ricardococati.carga.utils.DateServiceUtils.hojeEh;
import static com.ricardococati.carga.utils.DateServiceUtils.obterPrimeiroDiaUtilDaSemana;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DateServiceUtilsTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void obterPrimeiroDiaUtilDaSemanaSegunda() {
    //given
    LocalDate dataParam = LocalDate.of(2019,12,2);
    //when
    LocalDate result = obterPrimeiroDiaUtilDaSemana(dataParam);
    //then
    assertTrue(hojeEh(result.getDayOfWeek(), DayOfWeek.MONDAY));
  }

  @Test
  public void obterPrimeiroDiaUtilDaSemanaTerca() {
    //given
    LocalDate dataParam = LocalDate.of(2019,12,3);
    //when
    LocalDate result = obterPrimeiroDiaUtilDaSemana(dataParam);
    //then
    assertTrue(hojeEh(result.getDayOfWeek(), DayOfWeek.MONDAY));
  }

  @Test
  public void obterPrimeiroDiaUtilDaSemanaQuarta() {
    //given
    LocalDate dataParam = LocalDate.of(2019,12,4);
    //when
    LocalDate result = obterPrimeiroDiaUtilDaSemana(dataParam);
    //then
    assertTrue(hojeEh(result.getDayOfWeek(), DayOfWeek.MONDAY));
  }

  @Test
  public void obterPrimeiroDiaUtilDaSemanaQuinta() {
    //given
    LocalDate dataParam = LocalDate.of(2019,12,5);
    //when
    LocalDate result = obterPrimeiroDiaUtilDaSemana(dataParam);
    //then
    assertTrue(hojeEh(result.getDayOfWeek(), DayOfWeek.MONDAY));
  }

  @Test
  public void obterPrimeiroDiaUtilDaSemanaSexta() {
    //given
    LocalDate dataParam = LocalDate.of(2019,12,6);
    //when
    LocalDate result = obterPrimeiroDiaUtilDaSemana(dataParam);
    //then
    assertTrue(hojeEh(result.getDayOfWeek(), DayOfWeek.MONDAY));
  }

  @Test
  public void obterPrimeiroDiaUtilDaSemanaSabado() {
    //given
    LocalDate dataParam = LocalDate.of(2019,12,7);
    //when
    LocalDate result = obterPrimeiroDiaUtilDaSemana(dataParam);
    //then
    assertTrue(hojeEh(result.getDayOfWeek(), DayOfWeek.MONDAY));
  }

  @Test
  public void obterPrimeiroDiaUtilDaSemanaDomingo() {
    //given
    LocalDate dataParam = LocalDate.of(2019,12,8);
    //when
    LocalDate result = obterPrimeiroDiaUtilDaSemana(dataParam);
    //then
    assertTrue(hojeEh(result.getDayOfWeek(), DayOfWeek.MONDAY));
  }

}
