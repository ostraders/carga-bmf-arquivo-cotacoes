package com.ricardococati.carga.utils;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConverteDataParaNumeroSemanaAnoTest {

  @InjectMocks
  private ConverteDataParaNumeroSemanaAno target;

  @Test
  public void testConverte20131227() {
    final Optional<Integer> result =
        Optional.of(target.converte(LocalDate.of(2013, 12, 27)));
    assertEquals(Optional.of(52).get(), result.get());
  }

  @Test
  public void testConverte20131230() {
    final Optional<Integer> result =
        Optional.of(target.converte(LocalDate.of(2013, 12, 30)));
    assertEquals(Optional.of(1).get(), result.get());
  }

  @Test
  public void testConverte20140102() {
    final Optional<Integer> result =
        Optional.of(target.converte(LocalDate.of(2014, 01, 02)));
    assertEquals(Optional.of(1).get(), result.get());
  }

  @Test
  public void testConverte20140103() {
    final Optional<Integer> result =
        Optional.of(target.converte(LocalDate.of(2014, 01, 03)));
    assertEquals(Optional.of(1).get(), result.get());
  }

  @Test
  public void testConverte20140106() {
    final Optional<Integer> result =
        Optional.of(target.converte(LocalDate.of(2014, 01, 06)));
    assertEquals(Optional.of(2).get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana20131229_20140104_1() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(2013, 12, 30)));
    assertEquals(Optional.of("2013-12-29#2014-01-04").get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana20131229_20140104_2() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(2013, 12, 31)));
    assertEquals(Optional.of("2013-12-29#2014-01-04").get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana20131229_20140104_3() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(2014, 01, 01)));
    assertEquals(Optional.of("2013-12-29#2014-01-04").get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana20131229_20140104_4() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(2014, 01, 02)));
    assertEquals(Optional.of("2013-12-29#2014-01-04").get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana20131229_20140104_5() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(2014, 01, 03)));
    assertEquals(Optional.of("2013-12-29#2014-01-04").get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana20131229_20140104_6() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(2014, 01, 04)));
    assertEquals(Optional.of("2013-12-29#2014-01-04").get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana20140105_20140111_1() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(2014, 01, 05)));
    assertEquals(Optional.of("2014-01-05#2014-01-11").get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana20140105_20140111_2() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(2014, 01, 06)));
    assertEquals(Optional.of("2014-01-05#2014-01-11").get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana20191229_20200104() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(2019, 12, 30)));
    assertEquals(Optional.of("2019-12-29#2020-01-04").get(), result.get());
  }

  @Test
  public void testGeraDataInicialEFinalDaSemana19780212_19780218() {
    final Optional<String> result =
        Optional.of(target.geraDataInicialEFinalDaSemana(LocalDate.of(1978, 02, 16)));
    assertEquals(Optional.of("1978-02-12#1978-02-18").get(), result.get());
  }

}
