package com.ricardococati.carga.adapters.repositories;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_001;
import static com.ricardococati.templates.CandlestickSemanalTemplateLoader.CANDLESTICK_SEMANAL_VALID_001;
import static com.ricardococati.templates.CotacaoDTOTemplateLoader.COTACAO_DTO_VALID_021;
import static com.ricardococati.templates.HeaderDTOTemplateLoader.HEADER_DTO_VALID_001;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.carga.adapters.repositories.impl.CandlestickSemanalInserirDAOImpl;
import com.ricardococati.carga.adapters.repositories.impl.GeraSequenciaDAOImpl;
import com.ricardococati.carga.config.BaseJdbcTest;
import com.ricardococati.carga.adapters.repositories.sqlutil.CandlestickDiarioInserirSQLUtil;
import com.ricardococati.carga.adapters.repositories.sqlutil.CandlestickSemanalInserirSQLUtil;
import com.ricardococati.carga.adapters.repositories.sqlutil.CotacaoSQLUtil;
import com.ricardococati.carga.adapters.repositories.sqlutil.HeaderSQLUtil;
import com.ricardococati.util.InserirDadosPrimariosSemanalUtil;
import com.ricardococati.carga.entities.domains.CandlestickDiario;
import com.ricardococati.carga.entities.domains.CandlestickSemanal;
import com.ricardococati.carga.entities.dto.CotacaoDTO;
import com.ricardococati.carga.entities.dto.HeaderDTO;
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
public class CandlestickSemanalInserirDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private CandlestickSemanalInserirDAOImpl target;
  @Mock
  private GeraSequenciaDAOImpl genericDAO;
  @Mock
  private CandlestickSemanalInserirSQLUtil sqlUtil;
  @Mock
  private CandlestickDiarioInserirSQLUtil incluirDiarioSQLUtil;
  @Mock
  private CotacaoSQLUtil cotacaoSQLUtil;
  @Mock
  private HeaderSQLUtil headerSQLUtil;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates(
            "com.ricardococati.repository.dao.templates"
    );
    target = new CandlestickSemanalInserirDAOImpl(
            getNamedParameterJdbcTemplate(),
            genericDAO,
            sqlUtil
    );
    InserirDadosPrimariosSemanalUtil util = new InserirDadosPrimariosSemanalUtil(
            buildCotacaoDTO(),
            cotacaoSQLUtil,
            buildHeaderDTO(),
            getNamedParameterJdbcTemplate(),
            buildCandlestick(),
            sqlUtil,
            genericDAO,
            headerSQLUtil,
            incluirDiarioSQLUtil,
            buildCandlestickDiarioDTO()
    );
    util.incluiHeaderAntesDeExecutarTestes();
    util.incluiCotacaoAntesDeExecutarTestes();
    util.incluiCandleDiarioAntesDeExecutarTestes();
  }

  @Test
  public void incluirCandlestickSemanal() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CandlestickSemanal dto = buildCandlestick();
    //when
    Boolean retorno = target.incluirCandlestickSemanal(dto);
    //then
    assertTrue(retorno);
  }

  @Test
  public void incluirCandlestickSemanalNull() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na inserção de CANDLESTICK_SEMANAL");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCandlestickSemanal(null);
  }

  @Test
  public void incluirCandlestickSemanalCodnegNull() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CandlestickSemanal dto = buildCandlestick();
    dto.setCodneg(null);
    this.thrown.expectMessage("Violação de chave na inserção de CANDLESTICK_SEMANAL");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCandlestickSemanal(dto);
  }

  @Test
  public void incluirCandlestickSemanalDtpregNull() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CandlestickSemanal dto = buildCandlestick();
    dto.setDtpregini(null);
    this.thrown.expectMessage("Violação de chave na inserção de CANDLESTICK_SEMANAL");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCandlestickSemanal(dto);
  }

  @Test
  public void incluirCandlestickSemanalError() throws Exception {
    //given
    when(genericDAO.getSequence(any())).thenReturn(1);
    when(sqlUtil.getInsert()).thenReturn(",");
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CandlestickSemanal dto = buildCandlestick();
    this.thrown.expectMessage("Erro na execução do método CANDLESTICK_SEMANAL");
    this.thrown.expect(Exception.class);
    //when
    Boolean retorno = target.incluirCandlestickSemanal(dto);
  }

  private CandlestickSemanal buildCandlestick() {
    return from(CandlestickSemanal.class).gimme(CANDLESTICK_SEMANAL_VALID_001);
  }

  private CandlestickDiario buildCandlestickDiarioDTO() {
    return from(CandlestickDiario.class).gimme(CANDLESTICK_DIARIO_VALID_001);
  }

  private CotacaoDTO buildCotacaoDTO(){
    return from(CotacaoDTO.class).gimme(COTACAO_DTO_VALID_021);
  }

  private HeaderDTO buildHeaderDTO(){
    return from(HeaderDTO.class).gimme(HEADER_DTO_VALID_001);
  }

}