package com.ricardococati.carga.adapters.repositories;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_001;
import static com.ricardococati.carga.templates.CandlestickSemanalTemplateLoader.CANDLESTICK_SEMANAL_VALID_001;
import static com.ricardococati.carga.templates.CotacaoDTOTemplateLoader.COTACAO_DTO_VALID_021;
import static com.ricardococati.carga.templates.HeaderDTOTemplateLoader.HEADER_DTO_VALID_001;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.carga.adapters.repositories.candlestick.impl.CandlestickSemanalAtualizarDAOImpl;
import com.ricardococati.carga.adapters.repositories.gerasequencia.impl.GeraSequenciaDAOImpl;
import com.ricardococati.carga.config.BaseJdbcTest;
import com.ricardococati.carga.adapters.repositories.candlestick.sqlutil.CandlestickDiarioInserirSQLUtil;
import com.ricardococati.carga.adapters.repositories.candlestick.sqlutil.CandlestickSemanalAtualizarSQLUtil;
import com.ricardococati.carga.adapters.repositories.candlestick.sqlutil.CandlestickSemanalInserirSQLUtil;
import com.ricardococati.carga.adapters.repositories.cotacao.sqlutil.CotacaoSQLUtil;
import com.ricardococati.carga.adapters.repositories.header.sqlutil.HeaderSQLUtil;
import com.ricardococati.carga.util.InserirDadosPrimariosSemanalUtil;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanal;
import com.ricardococati.carga.entities.domains.splitinplit.SplitInplit;
import com.ricardococati.carga.entities.domains.cotacao.dto.CotacaoDTO;
import com.ricardococati.carga.entities.domains.header.dto.HeaderDTO;
import com.ricardococati.carga.entities.enums.OperacaoSplitInplit;
import java.time.LocalDate;
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
public class CandlestickSemanalAtualizarDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private CandlestickSemanalAtualizarDAOImpl target;
  @Mock
  private CandlestickSemanalAtualizarSQLUtil sqlUtil;
  @Mock
  private CandlestickSemanalInserirSQLUtil incluirSQLUtil;
  @Mock
  private CandlestickDiarioInserirSQLUtil incluirDiarioSQLUtil;
  @Mock
  private GeraSequenciaDAOImpl genericDAO;
  @Mock
  private CotacaoSQLUtil cotacaoSQLUtil;
  @Mock
  private HeaderSQLUtil headerSQLUtil;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.carga.templates");
    target = new CandlestickSemanalAtualizarDAOImpl(
            getNamedParameterJdbcTemplate(),
            sqlUtil
    );
    InserirDadosPrimariosSemanalUtil util = new InserirDadosPrimariosSemanalUtil(
            buildCotacaoDTO(),
            cotacaoSQLUtil,
            buildHeaderDTO(),
            getNamedParameterJdbcTemplate(),
            buildCandlestick(),
            incluirSQLUtil,
            genericDAO,
            headerSQLUtil,
            incluirDiarioSQLUtil,
            buildCandlestickDiarioDTO()
            );
    util.incluiHeaderAntesDeExecutarTestes();
    util.incluiCotacaoAntesDeExecutarTestes();
    util.incluiCandleDiarioAntesDeExecutarTestes();
    util.incluiCandleAntesDeExecutarTestes();
  }

  @Test
  public void atualizaInplit() throws Exception {
    SplitInplit splitInplit = build(LocalDate.now(), "INPLIT");
    when(sqlUtil.getUpdateSplitInplit(any())).thenCallRealMethod();
    when(sqlUtil.toParametersUpdateSplitInplit(any())).thenCallRealMethod();
    //when
    Boolean retorno = target.atualizaSplitInplit(splitInplit);
    //then
    assertTrue(retorno);
  }

  @Test
  public void atualizaSplit() throws Exception {
    SplitInplit splitInplit = build(LocalDate.now(), "SPLIT");
    when(sqlUtil.getUpdateSplitInplit(any())).thenCallRealMethod();
    when(sqlUtil.toParametersUpdateSplitInplit(any())).thenCallRealMethod();
    //when
    Boolean retorno = target.atualizaSplitInplit(splitInplit);
    //then
    assertTrue(retorno);
  }

  @Test
  public void updateSplitNull() throws Exception {
    //given
    when(sqlUtil.getUpdateSplitInplit(any())).thenCallRealMethod();
    when(sqlUtil.toParametersUpdateSplitInplit(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na atualização de CANDLESTICK_SEMANAL");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.atualizaSplitInplit(null);
  }

  @Test
  public void updateSplitCodnegNull() throws Exception {
    //given
    SplitInplit splitInplit = build(LocalDate.now(), "INPLIT");
    splitInplit.setCodneg(null);
    when(sqlUtil.getUpdateSplitInplit(any())).thenCallRealMethod();
    when(sqlUtil.toParametersUpdateSplitInplit(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na atualização de CANDLESTICK_SEMANAL");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.atualizaSplitInplit(splitInplit);
  }

  @Test
  public void updateSplitDtpregNull() throws Exception {
    //given
    SplitInplit splitInplit = build(LocalDate.now(), "INPLIT");
    splitInplit.setDtpreg(null);
    when(sqlUtil.getUpdateSplitInplit(any())).thenCallRealMethod();
    when(sqlUtil.toParametersUpdateSplitInplit(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na atualização de CANDLESTICK_SEMANAL");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.atualizaSplitInplit(splitInplit);
  }

  @Test
  public void updateSplitOperacaoNull() throws Exception {
    //given
    SplitInplit splitInplit = build(LocalDate.now(), "INPLIT");
    splitInplit.setOperacao(null);
    when(sqlUtil.getUpdateSplitInplit(any())).thenCallRealMethod();
    when(sqlUtil.toParametersUpdateSplitInplit(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na atualização de CANDLESTICK_SEMANAL");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.atualizaSplitInplit(splitInplit);
  }

  @Test
  public void updateSplitQtdSplitInplitNull() throws Exception {
    //given
    SplitInplit splitInplit = build(LocalDate.now(), "INPLIT");
    splitInplit.setQtdSplitInplit(null);
    when(sqlUtil.getUpdateSplitInplit(any())).thenCallRealMethod();
    when(sqlUtil.toParametersUpdateSplitInplit(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na atualização de CANDLESTICK_SEMANAL");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.atualizaSplitInplit(splitInplit);
  }

  @Test
  public void updateSplitError() throws Exception {
    //given
    SplitInplit splitInplit = build(LocalDate.now(), "INPLIT");
    when(sqlUtil.getUpdateSplitInplit(any())).thenReturn(",");
    when(sqlUtil.toParametersUpdateSplitInplit(any())).thenCallRealMethod();
    this.thrown.expectMessage("Erro na execução do método CANDLESTICK_SEMANAL");
    this.thrown.expect(Exception.class);
    //when
    Boolean retorno = target.atualizaSplitInplit(splitInplit);
  }

  private SplitInplit build(
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