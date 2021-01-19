package com.ricardococati.carga.usecases;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_001;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_002;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_003;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_004;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_005;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_006;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_007;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_008;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_009;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_010;
import static com.ricardococati.carga.templates.CandlestickSemanalTemplateLoader.CANDLESTICK_SEMANAL_VALID_001;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.carga.adapters.message.action.CandlestickActionListener;
import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickDiarioBuscarDAO;
import com.ricardococati.carga.adapters.repositories.candlestick.CandlestickSemanalInserirDAO;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanal;
import com.ricardococati.carga.usecases.candlestick.BuildCandlestickSemanalService;
import com.ricardococati.carga.usecases.candlestick.impl.CalculaCandlestickSemanalServiceImpl;
import com.ricardococati.carga.usecases.cotacao.converter.CandlestickConverter;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculaCandlestickSemanalServiceImplTest {

  @InjectMocks
  private CalculaCandlestickSemanalServiceImpl target;
  @Mock
  private CandlestickSemanalInserirDAO inserirSemanalDAO;
  @Mock
  private CandlestickDiarioBuscarDAO diarioDAO;
  @Mock
  private CandlestickActionListener listener;
  @Mock
  private CandlestickConverter candlestickConverter;
  @Mock
  private BuildCandlestickSemanalService buildSemanal;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.carga.templates");
  }

  @Test
  public void execute() throws Exception {
    //given
    when(diarioDAO.buscaCodNeg()).thenReturn(Arrays.asList("MGLU3"));
    when(diarioDAO.buscaCandleDiarioPorCodNegSemanaGerada(any()))
        .thenReturn(buildListCandleDiario());
    when(buildSemanal.build(any())).thenReturn(buildCandlestickSemanal());
    when(candlestickConverter.convertMessage(any())).thenCallRealMethod();
    //when
    Boolean result = target.execute();
    //then
    assertTrue(result);
    verify(this.diarioDAO, times(1)).buscaCodNeg();
  }

  @Test
  public void executeCodnegNull() throws Exception {
    //given
    when(diarioDAO.buscaCodNeg()).thenReturn(null);
    //when
    Boolean result = target.execute();
    //then
    assertTrue(result);
    verify(this.diarioDAO, times(1)).buscaCodNeg();
  }

  @Test
  public void executeError() throws Exception {
    //given
    when(diarioDAO.buscaCodNeg()).thenThrow(Exception.class);
    this.thrown.expect(Exception.class);
    this.thrown.expectMessage("Erro ao calcular Candlestick");
    //when
    target.execute();
  }

  @Test
  public void executeBuscaCandleDiarioPorCodNegSemanaGeradaError() throws Exception {
    //given
    when(diarioDAO.buscaCodNeg()).thenReturn(Arrays.asList("MGLU3"));
    when(diarioDAO.buscaCandleDiarioPorCodNegSemanaGerada(any()))
        .thenReturn(buildListCandleDiario());
    when(buildSemanal.build(any())).thenThrow(RuntimeException.class);
    this.thrown.expect(Exception.class);
    this.thrown.expectMessage("Erro ao calcular Candlestick");
    //when
    target.execute();
  }

  @Test
  public void executeBuildSemanalError() throws Exception {
    //given
    when(diarioDAO.buscaCodNeg()).thenReturn(Arrays.asList("MGLU3"));
    when(diarioDAO.buscaCandleDiarioPorCodNegSemanaGerada(any()))
        .thenThrow(RuntimeException.class);
    this.thrown.expect(Exception.class);
    this.thrown.expectMessage("Erro ao calcular Candlestick");
    //when
    target.execute();
  }

  @Test
  public void executeIncluirCandlestickSemanalError() throws Exception {
    //given
    when(diarioDAO.buscaCodNeg()).thenReturn(Arrays.asList("MGLU3"));
    when(diarioDAO.buscaCandleDiarioPorCodNegSemanaGerada(any()))
        .thenReturn(buildListCandleDiario());
    when(buildSemanal.build(any())).thenReturn(buildCandlestickSemanal());
    when(inserirSemanalDAO.incluirCandlestickSemanal(any()))
        .thenThrow(RuntimeException.class);
    this.thrown.expect(Exception.class);
    this.thrown.expectMessage("Erro ao calcular Candlestick");
    //when
    target.execute();
  }

  private List<CandlestickDiario> buildListCandleDiario() {
    return from(CandlestickDiario.class)
        .gimme(
            10,
            CANDLESTICK_DIARIO_VALID_001,
            CANDLESTICK_DIARIO_VALID_002,
            CANDLESTICK_DIARIO_VALID_003,
            CANDLESTICK_DIARIO_VALID_004,
            CANDLESTICK_DIARIO_VALID_005,
            CANDLESTICK_DIARIO_VALID_006,
            CANDLESTICK_DIARIO_VALID_007,
            CANDLESTICK_DIARIO_VALID_008,
            CANDLESTICK_DIARIO_VALID_009,
            CANDLESTICK_DIARIO_VALID_010
        );
  }

  private CandlestickSemanal buildCandlestickSemanal(){
    return from(CandlestickSemanal.class).gimme(CANDLESTICK_SEMANAL_VALID_001);
  }
}