package com.ricardococati.repository.dao.impl;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.repository.dao.templates.CandlestickSemanalTemplateLoader.CANDLESTICK_SEMANAL_VALID_001;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.model.entities.CandlestickSemanal;
import com.ricardococati.repository.dao.BaseJdbcTest;
import com.ricardococati.repository.dao.GeraSequenciaDAO;
import com.ricardococati.repository.dao.mapper.CandlestickSemanalMapper;
import com.ricardococati.repository.dao.sqlutil.CandlestickSemanalBuscarSQLUtil;
import com.ricardococati.repository.dao.sqlutil.CandlestickSemanalInserirSQLUtil;
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
public class CandlestickSemanalBuscarDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private CandlestickSemanalBuscarDAOImpl target;
  @Mock
  private CandlestickSemanalMapper mapper;
  @Mock
  private CandlestickSemanalBuscarSQLUtil sqlUtil;
  @MockBean
  private CandlestickSemanalInserirDAOImpl incluirDAO;
  @Mock
  private CandlestickSemanalInserirSQLUtil incluirSQLUtil;
  @Mock
  private GeraSequenciaDAO genericDAO;

  @Before
  public void setUp() {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.repository.dao.templates");
    target = new CandlestickSemanalBuscarDAOImpl(getNamedParameterJdbcTemplate(), sqlUtil, mapper);
    incluiCandleAntesDeExecutarTestes();
  }

  @Test
  public void buscarCandleSemanalPorPrimeiroDiaSemana() {
    //given
    LocalDate dtpregLocal = LocalDate.of(1978, 2, 17);
    when(sqlUtil.getSelectCandleSemanalByDtPregIni()).thenCallRealMethod();
    when(sqlUtil.toParametersCandleSemanalByDtPregIni(any())).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<CandlestickSemanal> result = target.buscarCandleSemanalPorPrimeiroDiaSemana(dtpregLocal);
    //then
    assertTrue(!result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(1);
    assertThat(result.get(0).getCodneg()).isNotNull().isEqualTo("MGLU3");
    assertThat(result.get(0).getPreult()).isNotNull().isEqualTo(new BigDecimal("11.10"));
  }

  private void incluiCandleAntesDeExecutarTestes() {
    incluirDAO = new CandlestickSemanalInserirDAOImpl(
        getNamedParameterJdbcTemplate(),
        genericDAO,
        incluirSQLUtil
    );
    when(incluirSQLUtil.getInsert()).thenCallRealMethod();
    when(incluirSQLUtil.toParameters(any())).thenCallRealMethod();
    when(genericDAO.getSequence(any())).thenReturn(1);
    incluirDAO.incluirCandlestickSemanal(buildCandlestick());
  }

  private CandlestickSemanal buildCandlestick() {
    return from(CandlestickSemanal.class).gimme(CANDLESTICK_SEMANAL_VALID_001);
  }

}