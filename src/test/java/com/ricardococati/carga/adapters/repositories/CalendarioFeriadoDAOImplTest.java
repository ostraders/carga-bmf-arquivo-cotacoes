package com.ricardococati.carga.adapters.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.ricardococati.carga.adapters.repositories.calendario.impl.CalendarioFeriadoDAOImpl;
import com.ricardococati.carga.config.BaseJdbcTest;
import com.ricardococati.carga.adapters.repositories.calendario.sqlutil.CalendarioFeriadoSQLUtil;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CalendarioFeriadoDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private CalendarioFeriadoDAOImpl target;
  @Mock
  private CalendarioFeriadoSQLUtil sqlUtil;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    target = new CalendarioFeriadoDAOImpl(getNamedParameterJdbcTemplate(), sqlUtil);
  }

  @Test
  public void buscaCalendarioFeriadoDataComum() throws Exception {
    //given
    LocalDate dtpregLocal = LocalDate.of(2020,1,2);
    when(sqlUtil.getSelectCountByDataAtual()).thenCallRealMethod();
    when(sqlUtil.toParametersSelectByDataAtual(any())).thenCallRealMethod();
    //when
    Boolean result = target.buscaCalendarioFeriado(dtpregLocal);
    //then
    assertFalse(result);
  }

  @Test
  public void buscaCalendarioFeriadoDataFeriado() throws Exception {
    //given
    LocalDate dtpregLocal = LocalDate.of(2020, 1,1);
    when(sqlUtil.getSelectCountByDataAtual()).thenCallRealMethod();
    when(sqlUtil.toParametersSelectByDataAtual(any())).thenCallRealMethod();
    //when
    Boolean result = target.buscaCalendarioFeriado(dtpregLocal);
    //then
    assertTrue(result);
  }

  @Test
  public void buscaCalendarioFeriadoDataFeriadoNatal() throws Exception {
    //given
    LocalDate dtpregLocal = LocalDate.of(2020, 12,25);
    when(sqlUtil.getSelectCountByDataAtual()).thenCallRealMethod();
    when(sqlUtil.toParametersSelectByDataAtual(any())).thenCallRealMethod();
    //when
    Boolean result = target.buscaCalendarioFeriado(dtpregLocal);
    //then
    assertTrue(result);
  }

  @Test
  public void buscaCalendarioFeriadoDataNull() throws Exception {
    //given
    when(sqlUtil.getSelectCountByDataAtual()).thenCallRealMethod();
    when(sqlUtil.toParametersSelectByDataAtual(any())).thenCallRealMethod();
    this.thrown.expectMessage("Data atual está nula para pesquisar CALENDARIO_FERIADO");
    this.thrown.expect(NullPointerException.class);
    //when
    Boolean result = target.buscaCalendarioFeriado(null);
  }

  @Test
  public void buscaCalendarioFeriadoErroSelect() throws Exception {
    //given
    when(sqlUtil.getSelectCountByDataAtual()).thenReturn(",");
    when(sqlUtil.toParametersSelectByDataAtual(any())).thenCallRealMethod();
    this.thrown.expectMessage("Erro na execução do método CALENDARIO_FERIADO");
    this.thrown.expect(Exception.class);
    //when
    Boolean result = target.buscaCalendarioFeriado(LocalDate.now());
  }

}
