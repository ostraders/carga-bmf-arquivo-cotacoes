package com.ricardococati.repository.dao.impl;

import com.ricardococati.repository.dao.BaseJdbcTest;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GeraSequenciaDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private GeraSequenciaDAOImpl target;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    target = new GeraSequenciaDAOImpl(
        getNamedParameterJdbcTemplate()
    );
  }

  @Test
  public void getSequenceGeraDuasSequencias() {
    //given
    String sequence = "ARQUIVO_SEQ";
    //when
    Number retorno1 = target.getSequence(sequence);
    Number retorno2 = target.getSequence(sequence);
    //then
    Assertions.assertThat(retorno1).isNotNull().isEqualTo(1L);
    Assertions.assertThat(retorno2).isNotNull().isEqualTo(2L);
  }

  @Test
  public void getSequenceCandleDiario() {
    //given
    String sequence = "CANDLESTICK_SEQ";
    //when
    Number retorno = target.getSequence(sequence);
    //then
    Assertions.assertThat(retorno).isNotNull().isEqualTo(1L);
  }

  @Test
  public void getSequenceCalendarioFeriado() {
    //given
    String sequence = "CALENDARIO_FERIADO_SEQ";
    //when
    Number retorno = target.getSequence(sequence);
    //then
    Assertions.assertThat(retorno).isNotNull().isEqualTo(1L);
  }

  @Test
  public void getSequenceSetor() {
    //given
    String sequence = "SETOR_SEQ";
    //when
    Number retorno = target.getSequence(sequence);
    //then
    Assertions.assertThat(retorno).isNotNull().isEqualTo(1L);
  }

  @Test
  public void getSequenceEmpresa() {
    //given
    String sequence = "EMPRESA_SEQ";
    //when
    Number retorno = target.getSequence(sequence);
    //then
    Assertions.assertThat(retorno).isNotNull().isEqualTo(1L);
  }

  @Test
  public void getSequenceNull() {
    //given
    String sequence = null;
    //when
    this.thrown.expectMessage("A sequencia enviada não pode ser nula!");
    this.thrown.expect(IllegalArgumentException.class);
    target.getSequence(sequence);
  }
}