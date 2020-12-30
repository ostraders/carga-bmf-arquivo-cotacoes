package com.ricardococati.carga.usecases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ricardococati.carga.adapters.repositories.CandlestickDiarioAtualizarDAO;
import com.ricardococati.carga.adapters.repositories.CandlestickSemanalAtualizarDAO;
import com.ricardococati.carga.entities.domains.SplitInplit;
import com.ricardococati.carga.entities.enums.OperacaoSplitInplit;
import com.ricardococati.carga.usecases.impl.AtualizarCandlesticksServiceImpl;
import java.time.LocalDate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AtualizarCandlesticksServiceImplTest {

  @InjectMocks
  private AtualizarCandlesticksServiceImpl target;
  @Mock
  private CandlestickDiarioAtualizarDAO atualizarDiarioDAO;
  @Mock
  private CandlestickSemanalAtualizarDAO atualizarSemanalDAO;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void executeInplit() throws Exception {
    //given
    when(atualizarDiarioDAO.atualizaSplitInplit(any())).thenReturn(Boolean.TRUE);
    when(atualizarSemanalDAO.atualizaSplitInplit(any())).thenReturn(Boolean.TRUE);
    //when
    Boolean result = target.executeSplitInplit(buildSplitInplit(LocalDate.now(), "INPLIT"));
    //then
    assertTrue(result);
    verify(this.atualizarDiarioDAO, times(1))
        .atualizaSplitInplit(any());
    verify(this.atualizarSemanalDAO, times(1))
        .atualizaSplitInplit(any());
  }

  @Test
  public void executeSplit() throws Exception {
    //given
    when(atualizarDiarioDAO.atualizaSplitInplit(any())).thenReturn(Boolean.TRUE);
    when(atualizarSemanalDAO.atualizaSplitInplit(any())).thenReturn(Boolean.TRUE);
    //when
    Boolean result = target.executeSplitInplit(buildSplitInplit(LocalDate.now(), "SPLIT"));
    //then
    assertTrue(result);
    verify(this.atualizarDiarioDAO, times(1))
        .atualizaSplitInplit(any());
    verify(this.atualizarSemanalDAO, times(1))
        .atualizaSplitInplit(any());
  }

  @Test
  public void executeInplitDiarioFalse() throws Exception {
    //given
    when(atualizarDiarioDAO.atualizaSplitInplit(any())).thenReturn(Boolean.FALSE);
    when(atualizarSemanalDAO.atualizaSplitInplit(any())).thenReturn(Boolean.TRUE);
    //when
    Boolean result = target.executeSplitInplit(buildSplitInplit(LocalDate.now(), "SPLIT"));
    //then
    assertFalse(result);
    verify(this.atualizarDiarioDAO, times(1))
        .atualizaSplitInplit(any());
    verify(this.atualizarSemanalDAO, times(0))
        .atualizaSplitInplit(any());
  }

  @Test
  public void executeInplitSemanalFalse() throws Exception {
    //given
    when(atualizarDiarioDAO.atualizaSplitInplit(any())).thenReturn(Boolean.TRUE);
    when(atualizarSemanalDAO.atualizaSplitInplit(any())).thenReturn(Boolean.FALSE);
    //when
    Boolean result = target.executeSplitInplit(buildSplitInplit(LocalDate.now(), "SPLIT"));
    //then
    assertFalse(result);
    verify(this.atualizarDiarioDAO, times(1))
        .atualizaSplitInplit(any());
    verify(this.atualizarSemanalDAO, times(1))
        .atualizaSplitInplit(any());
  }

  @Test
  public void executeInplitDiarioError() throws Exception {
    //given
    when(atualizarDiarioDAO.atualizaSplitInplit(any())).thenThrow(Exception.class);
    when(atualizarSemanalDAO.atualizaSplitInplit(any())).thenReturn(Boolean.FALSE);
    this.thrown.expect(Exception.class);
    //when
    target.executeSplitInplit(buildSplitInplit(LocalDate.now(), "SPLIT"));
  }

  @Test
  public void executeInplitSemanalError() throws Exception {
    //given
    when(atualizarDiarioDAO.atualizaSplitInplit(any())).thenReturn(Boolean.TRUE);
    when(atualizarSemanalDAO.atualizaSplitInplit(any())).thenThrow(Exception.class);
    this.thrown.expect(Exception.class);
    //when
    target.executeSplitInplit(buildSplitInplit(LocalDate.now(), "SPLIT"));
  }

  private SplitInplit buildSplitInplit(
      final LocalDate dtpreg,
      final String operacao
  ) {
    return SplitInplit
        .builder()
        .codneg("MGLU3")
        .dtpreg(dtpreg)
        .qtdSplitInplit(2)
        .operacao(OperacaoSplitInplit.valueOf(operacao))
        .build();
  }

}
