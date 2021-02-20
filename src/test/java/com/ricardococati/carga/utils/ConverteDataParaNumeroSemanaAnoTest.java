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

}
