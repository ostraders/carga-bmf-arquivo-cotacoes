package com.ricardococati.carga.adapters.repositories;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_001;
import static com.ricardococati.carga.templates.CotacaoDTOTemplateLoader.COTACAO_DTO_VALID_021;
import static com.ricardococati.carga.templates.HeaderDTOTemplateLoader.HEADER_DTO_VALID_001;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.carga.adapters.repositories.candlestick.impl.CandlestickDiarioBuscarDAOImpl;
import com.ricardococati.carga.adapters.repositories.candlestick.impl.CandlestickDiarioInserirDAOImpl;
import com.ricardococati.carga.adapters.repositories.gerasequencia.impl.GeraSequenciaDAOImpl;
import com.ricardococati.carga.config.BaseJdbcTest;
import com.ricardococati.carga.adapters.repositories.candlestick.mapper.CandlestickDiarioMapper;
import com.ricardococati.carga.adapters.repositories.candlestick.sqlutil.CandlestickDiarioBuscarSQLUtil;
import com.ricardococati.carga.adapters.repositories.candlestick.sqlutil.CandlestickDiarioInserirSQLUtil;
import com.ricardococati.carga.adapters.repositories.cotacao.sqlutil.CotacaoSQLUtil;
import com.ricardococati.carga.adapters.repositories.header.sqlutil.HeaderSQLUtil;
import com.ricardococati.carga.util.InserirDadosPrimariosDiarioUtil;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.cotacao.dto.CotacaoDTO;
import com.ricardococati.carga.entities.domains.header.dto.HeaderDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
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
  private CotacaoSQLUtil cotacaoSQLUtil;
  @Mock
  private HeaderSQLUtil headerSQLUtil;
  @Mock
  private CandlestickDiarioInserirSQLUtil incluirSQLUtil;
  @Mock
  private GeraSequenciaDAOImpl genericDAO;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.carga.templates");
    target = new CandlestickDiarioBuscarDAOImpl(
        getNamedParameterJdbcTemplate(),
        sqlUtil,
        mapper
    );
    InserirDadosPrimariosDiarioUtil util = new InserirDadosPrimariosDiarioUtil(
            buildCotacaoDTO(),
            cotacaoSQLUtil,
            buildHeaderDTO(),
            getNamedParameterJdbcTemplate(),
            buildCandlestickDiarioDTO(),
            incluirSQLUtil,
            genericDAO,
            headerSQLUtil
    );
    util.incluiHeaderAntesDeExecutarTestes();
    util.incluiCotacaoAntesDeExecutarTestes();
    util.incluiCandleAntesDeExecutarTestes();
  }

  @Test
  public void buscarCandleDiarioPorPrimeiroDiaSemana() throws Exception {
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

  @Test
  public void buscarCandleDiarioPorPrimeiroDiaSemanaCodnegNull() throws Exception {
    //given
    LocalDate dtpregLocal = LocalDate.of(1978, 2, 17);
    when(sqlUtil.getSelectCandleDiarioByDtPregCodneg()).thenCallRealMethod();
    when(sqlUtil.toParametersCandleDiarioByDtPregCodneg(any(), any())).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na inserção de CANDLESTICK_DIARIO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    List<CandlestickDiario> result =
        target.buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(dtpregLocal, null);
  }

  @Test
  public void buscarCandleDiarioPorPrimeiroDiaSemanaDtpregNull() throws Exception {
    //given
    when(sqlUtil.getSelectCandleDiarioByDtPregCodneg()).thenCallRealMethod();
    when(sqlUtil.toParametersCandleDiarioByDtPregCodneg(any(), any())).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na inserção de CANDLESTICK_DIARIO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    List<CandlestickDiario> result =
        target.buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(
            null,
            "MGLU3"
        );
  }

  @Test
  public void buscarCandleDiarioPorPrimeiroDiaSemanaError() throws Exception {
    //given
    LocalDate dtpregLocal = LocalDate.of(1978, 2, 17);
    when(sqlUtil.getSelectCandleDiarioByDtPregCodneg()).thenReturn(",");
    when(sqlUtil.toParametersCandleDiarioByDtPregCodneg(any(), any())).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    this.thrown.expectMessage("Erro na execução do método CANDLESTICK_DIARIO");
    this.thrown.expect(Exception.class);
    //when
    List<CandlestickDiario> result =
        target.buscarCandleDiarioPorPrimeiroDiaSemanaCodneg(
            dtpregLocal,
            "MGLU3"
        );
  }

  @Test
  public void buscaCandleDiarioPorCodNegSemanaGerada() throws Exception {
    //given
    when(sqlUtil.getSelectByCodNegESemana()).thenCallRealMethod();
    when(sqlUtil.toParametersSelectByCodNeg(any())).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<CandlestickDiario> result =
        target.buscaCandleDiarioPorCodNegSemanaGerada("MGLU3");
    //then
    assertTrue(!result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(1);
    assertThat(result.get(0).getCodneg()).isNotNull().isEqualTo("MGLU3");
    assertThat(result.get(0).getPreult()).isNotNull().isEqualTo(new BigDecimal("11.10"));
  }

  @Test
  public void buscaCandleDiarioPorCodNegSemanaGeradaDtpregNull() throws Exception {
    //given
    when(sqlUtil.getSelectByCodNegESemana()).thenCallRealMethod();
    when(sqlUtil.toParametersSelectByCodNeg(any())).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na inserção de CANDLESTICK_DIARIO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    List<CandlestickDiario> result =
        target.buscaCandleDiarioPorCodNegSemanaGerada(null);
  }

  @Test
  public void buscaCandleDiarioPorCodNegSemanaGeradaError() throws Exception {
    //given
    when(sqlUtil.getSelectByCodNegESemana()).thenReturn(",");
    when(sqlUtil.toParametersSelectByCodNeg(any())).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    this.thrown.expectMessage("Erro na execução do método CANDLESTICK_DIARIO");
    this.thrown.expect(Exception.class);
    //when
    List<CandlestickDiario> result =
        target.buscaCandleDiarioPorCodNegSemanaGerada("MGLU3");
  }

  @Test
  public void buscaCodNeg() throws Exception {
    //given
    when(sqlUtil.getSelectCodNeg()).thenCallRealMethod();
    when(mapper.mapperCodNeg(any())).thenCallRealMethod();
    //when
    List<String> result = target.buscaCodNeg();
    //then
    assertTrue(!result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(1);
    assertThat(result.get(0)).isNotNull().isEqualTo("MGLU3");
  }

  @Test
  public void buscaCodNegError() throws Exception {
    //given
    when(sqlUtil.getSelectCodNeg()).thenReturn(",");
    when(mapper.mapperCodNeg(any())).thenCallRealMethod();
    this.thrown.expectMessage("Erro na execução do método CANDLESTICK_DIARIO");
    this.thrown.expect(Exception.class);
    //when
    List<String> result = target.buscaCodNeg();
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