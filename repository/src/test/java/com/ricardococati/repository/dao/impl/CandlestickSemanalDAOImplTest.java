package com.ricardococati.repository.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.ricardococati.model.entities.CandlestickSemanal;
import com.ricardococati.repository.dao.BaseJdbcTest;
import com.ricardococati.repository.dao.GenericDAO;
import com.ricardococati.repository.dao.mapper.CandlestickSemanalMapper;
import com.ricardococati.repository.dao.sqlutil.CandlestickSemanalSQLUtil;
import com.ricardococati.repository.dao.sqlutil.InserirCandlestickSemanalSQLUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CandlestickSemanalDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private CandlestickSemanalDAOImpl target;
  @Mock
  private CandlestickSemanalMapper mapper;
  @Mock
  private CandlestickSemanalSQLUtil sqlUtil;
  @MockBean
  private InserirCandlestickSemanalDAOImpl incluirDAO;
  @Mock
  private InserirCandlestickSemanalSQLUtil incluirSQLUtil;
  @Mock
  private GenericDAO genericDAO;

  @Before
  public void setUp() {
    target = new CandlestickSemanalDAOImpl(getNamedParameterJdbcTemplate(), sqlUtil, mapper);
    incluiCandleAntesDeExecutarTestes();
  }

  @Test
  public void buscarCandleSemanalPorPrimeiroDiaSemana() {
    //given
    LocalDate dtpregLocal = LocalDate.now();
    when(sqlUtil.getSelectCandleSemanalByDtPregIni()).thenCallRealMethod();
    when(sqlUtil.toParametersCandleSemanalByDtPregIni(any())).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<CandlestickSemanal> result = target.buscarCandleSemanalPorPrimeiroDiaSemana(dtpregLocal);
    //then
    assertTrue(!result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(1);
    assertThat(result.get(0).getCodneg()).isNotNull().isEqualTo("MGLU3");
    assertThat(result.get(0).getPreult()).isNotNull().isEqualTo(new BigDecimal("10.10"));
  }

  private void incluiCandleAntesDeExecutarTestes() {
    incluirDAO = new InserirCandlestickSemanalDAOImpl(getNamedParameterJdbcTemplate(), genericDAO, incluirSQLUtil);
    when(incluirSQLUtil.getInsert()).thenCallRealMethod();
    when(incluirSQLUtil.toParameters(any())).thenCallRealMethod();
    when(genericDAO.getSequence(any(), any())).thenReturn(1);
    incluirDAO.incluirCandlestickSemanal(
        buildCandlestickSemanalDTO("MGLU3", 10.1, LocalDate.now(), LocalDate.now().plusDays(1))
    );
  }

  private CandlestickSemanal buildCandlestickSemanalDTO(
      final String codneg,
      final Double preult,
      final LocalDate dtpregini,
      final LocalDate dtpregfim
  ) {
    return CandlestickSemanal
        .builder()
        .dtpregini(dtpregini)
        .dtpregfim(dtpregfim)
        .codneg(codneg)
        .preult(new BigDecimal(preult).setScale(4, BigDecimal.ROUND_HALF_UP))
        .build();
  }

}