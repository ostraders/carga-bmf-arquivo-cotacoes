package com.ricardococati.repository.dao.impl;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.repository.dao.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_001;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.model.entities.CandlestickDiario;
import com.ricardococati.repository.dao.BaseJdbcTest;
import com.ricardococati.repository.dao.sqlutil.CandlestickDiarioInserirSQLUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CandlestickDiarioInserirDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private CandlestickDiarioInserirDAOImpl target;
  @Mock
  private GeraSequenciaDAOImpl genericDAO;
  @Mock
  private CandlestickDiarioInserirSQLUtil sqlUtil;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.repository.dao.templates");
    target = new CandlestickDiarioInserirDAOImpl(getNamedParameterJdbcTemplate(), genericDAO, sqlUtil);
  }

  @Test
  public void incluirCandlestickDiario() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CandlestickDiario dto = buildCandlestick();
    //when
    Boolean retorno = target.incluirCandlestickDiario(dto);
    //then
    assertTrue(retorno);
  }

  @Test
  public void incluirCandlestickDiarioNull() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na inserção de CANDLESTICK_DIARIO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCandlestickDiario(null);
  }

  @Test
  public void incluirCandlestickDiarioCodnegNull() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CandlestickDiario dto = buildCandlestick();
    dto.setCodneg(null);
    this.thrown.expectMessage("Violação de chave na inserção de CANDLESTICK_DIARIO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCandlestickDiario(dto);
  }

  @Test
  public void incluirCandlestickDiarioDtpregNull() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CandlestickDiario dto = buildCandlestick();
    dto.setDtpreg(null);
    this.thrown.expectMessage("Violação de chave na inserção de CANDLESTICK_DIARIO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCandlestickDiario(dto);
  }

  @Test
  public void incluirCandlestickDiarioError() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenReturn(",");
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CandlestickDiario dto = buildCandlestick();
    this.thrown.expectMessage("Erro na execução do método CANDLESTICK_DIARIO");
    this.thrown.expect(Exception.class);
    //when
    Boolean retorno = target.incluirCandlestickDiario(dto);
  }

  private CandlestickDiario buildCandlestick() {
    return from(CandlestickDiario.class).gimme(CANDLESTICK_DIARIO_VALID_001);
  }

}