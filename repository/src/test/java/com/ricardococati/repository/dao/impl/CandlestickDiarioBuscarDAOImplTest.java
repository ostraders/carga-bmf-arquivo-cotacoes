package com.ricardococati.repository.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.repository.dao.BaseJdbcTest;
import com.ricardococati.repository.dao.GeraSequenciaDAO;
import com.ricardococati.repository.dao.mapper.CandlestickDiarioMapper;
import com.ricardococati.repository.dao.sqlutil.CandlestickDiarioBuscarSQLUtil;
import com.ricardococati.repository.dao.sqlutil.CandlestickDiarioInserirSQLUtil;
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
public class CandlestickDiarioBuscarDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private CandlestickDiarioBuscarDAOImpl target;
  @Mock
  private CandlestickDiarioMapper mapper;
  @Mock
  private CandlestickDiarioBuscarSQLUtil sqlUtil;
  @MockBean
  private CandlestickDiarioInserirDAOImpl incluirDAO;
  @Mock
  private CandlestickDiarioInserirSQLUtil incluirSQLUtil;
  @Mock
  private GeraSequenciaDAO genericDAO;

  @Before
  public void setUp() {
    target = new CandlestickDiarioBuscarDAOImpl(getNamedParameterJdbcTemplate(), sqlUtil, mapper);
    incluiCandleAntesDeExecutarTestes();
  }

  @Test
  public void buscarCandleDiarioPorPrimeiroDiaSemana() {
    //given
    LocalDate dtpregLocal = LocalDate.now();
    when(sqlUtil.getSelectCandleDiarioByDtPregCodneg()).thenCallRealMethod();
    when(sqlUtil.toParametersCandleDiarioByDtPregCodneg(any(), any())).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<CandlestickDiario> result =
        target.buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(dtpregLocal, "MGLU3");
    //then
    assertTrue(!result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(1);
    assertThat(result.get(0).getCodneg()).isNotNull().isEqualTo("MGLU3");
    assertThat(result.get(0).getPreult()).isNotNull().isEqualTo(new BigDecimal("10.10"));
  }

  private void incluiCandleAntesDeExecutarTestes() {
    incluirDAO = new CandlestickDiarioInserirDAOImpl(getNamedParameterJdbcTemplate(), genericDAO, incluirSQLUtil);
    when(incluirSQLUtil.getInsert()).thenCallRealMethod();
    when(incluirSQLUtil.toParameters(any())).thenCallRealMethod();
    when(genericDAO.getSequence(any())).thenReturn(1);
    incluirDAO.incluirCandlestickDiario(
        buildCandlestickDiarioDTO("MGLU3", 10.1, LocalDate.now())
    );
  }

  private CandlestickDiario buildCandlestickDiarioDTO(
      final String codneg,
      final Double preult,
      final LocalDate dtpreg
  ) {
    return CandlestickDiario
        .builder()
        .dtpreg(dtpreg)
        .codneg(codneg)
        .preult(new BigDecimal(preult).setScale(4, BigDecimal.ROUND_HALF_UP))
        .build();
  }

}