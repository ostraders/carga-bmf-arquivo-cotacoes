package com.ricardococati.carga.usecases;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_INVALID;
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
import static com.ricardococati.carga.templates.CandlestickDiarioTemplateLoader.CANDLESTICK_DIARIO_VALID_021;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickDiario;
import com.ricardococati.carga.entities.domains.candlestick.CandlestickSemanal;
import com.ricardococati.carga.usecases.candlestick.impl.BuildCandlestickSemanalServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuildCandlestickSemanalServiceImplTest {

  @InjectMocks
  private BuildCandlestickSemanalServiceImpl target;

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.carga.templates");
  }

  @Test
  public void testBuild() {
    final List<CandlestickDiario> diarioList = buildListCandleDiario();
    CandlestickSemanal result = target.build(diarioList);
    assertThat(result.getPremin()).isNotNull().isEqualTo(new BigDecimal("1.1000"));
    assertThat(result.getPremax()).isNotNull().isEqualTo(new BigDecimal("92.1000"));
    assertThat(result.getPreabe()).isNotNull().isEqualTo(new BigDecimal("9.1000"));
    assertThat(result.getPreult()).isNotNull().isEqualTo(new BigDecimal("11.9000"));
    assertThat(result.getVoltot()).isNotNull().isEqualTo(new BigDecimal("4600000.0000"));
    assertThat(result.getDtpregini()).isNotNull().isEqualTo(LocalDate.of(1978,02,17));
    assertThat(result.getDtpregfim()).isNotNull().isEqualTo(LocalDate.of(1978,02,26));
  }

  @Test
  public void testBuildSmallerThan() {
    final List<CandlestickDiario> diarioList = buildListCandleDiario();
    diarioList.add(buildCandlestickDiarioDTO1());
    CandlestickSemanal result = target.build(diarioList);
    assertThat(result.getPremin()).isNotNull().isEqualTo(new BigDecimal("0.9000"));
    assertThat(result.getPremax()).isNotNull().isEqualTo(new BigDecimal("122.0000"));
    assertThat(result.getPreabe()).isNotNull().isEqualTo(new BigDecimal("9.1000"));
    assertThat(result.getPreult()).isNotNull().isEqualTo(new BigDecimal("199.3000"));
    assertThat(result.getVoltot()).isNotNull().isEqualTo(new BigDecimal("4700000.0000"));
    assertThat(result.getDtpregini()).isNotNull().isEqualTo(LocalDate.of(1978,02,17));
    assertThat(result.getDtpregfim()).isNotNull().isEqualTo(LocalDate.of(2021,02,16));
  }

  @Test
  public void testBuildInvalidValues() {
    final List<CandlestickDiario> diarioList = buildListCandleDiario();
    diarioList.add(buildCandlestickDiarioDTO1());
    diarioList.add(buildCandlestickDiarioDTO2());
    CandlestickSemanal result = target.build(diarioList);
    assertThat(result.getPremin()).isNotNull().isEqualTo(new BigDecimal("0.9000"));
    assertThat(result.getPremax()).isNotNull().isEqualTo(new BigDecimal("122.0000"));
    assertThat(result.getPreabe()).isNotNull().isEqualTo(new BigDecimal("9.1000"));
    assertThat(result.getPreult()).isNotNull().isEqualTo(new BigDecimal("199.3000"));
    assertThat(result.getVoltot()).isNotNull().isEqualTo(new BigDecimal("4700000.0000"));
    assertThat(result.getDtpregini()).isNotNull().isEqualTo(LocalDate.of(1978,02,17));
    assertThat(result.getDtpregfim()).isNotNull().isEqualTo(LocalDate.of(2021,02,16));
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

  private CandlestickDiario buildCandlestickDiarioDTO1() {
    return from(CandlestickDiario.class).gimme(CANDLESTICK_DIARIO_VALID_021);
  }

  private CandlestickDiario buildCandlestickDiarioDTO2() {
    return from(CandlestickDiario.class).gimme(CANDLESTICK_DIARIO_INVALID);
  }

}
