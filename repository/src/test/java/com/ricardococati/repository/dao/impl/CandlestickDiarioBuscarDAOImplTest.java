package com.ricardococati.repository.dao.impl;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.repository.dao.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_001;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
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
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.repository.dao.templates");
    target = new CandlestickDiarioBuscarDAOImpl(getNamedParameterJdbcTemplate(), sqlUtil, mapper);
    incluiCandleAntesDeExecutarTestes();
  }

  @Test
  public void buscarCandleDiarioPorPrimeiroDiaSemana() {
    //given
    LocalDate dtpregLocal = LocalDate.of(1978, 2, 17);
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
    assertThat(result.get(0).getPreult()).isNotNull().isEqualTo(new BigDecimal("11.10"));
  }

  private void incluiCandleAntesDeExecutarTestes() throws Exception {
    incluirDAO = new CandlestickDiarioInserirDAOImpl(
        getNamedParameterJdbcTemplate(),
        genericDAO,
        incluirSQLUtil
    );
    when(incluirSQLUtil.getInsert()).thenCallRealMethod();
    when(incluirSQLUtil.toParameters(any())).thenCallRealMethod();
    when(genericDAO.getSequence(any())).thenReturn(1);
    incluirDAO.incluirCandlestickDiario(buildCandlestickDiarioDTO());
  }

  private CandlestickDiario buildCandlestickDiarioDTO() {
    return from(CandlestickDiario.class).gimme(CANDLESTICK_DIARIO_VALID_001);
  }

}