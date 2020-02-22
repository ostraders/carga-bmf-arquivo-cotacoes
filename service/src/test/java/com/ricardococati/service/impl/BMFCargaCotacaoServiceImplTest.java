package com.ricardococati.service.impl;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.service.templates.CotacaoTemplateLoader.COTACAO_VALID_001;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.model.dto.Cotacao;
import com.ricardococati.model.entities.EmpresaAtivo;
import com.ricardococati.repository.dao.CandlestickDiarioInserirDAO;
import com.ricardococati.repository.dao.CotacaoInserirDAO;
import com.ricardococati.repository.dao.EmpresaAtivoBuscarDAO;
import com.ricardococati.repository.event.PostgresEventListener;
import com.ricardococati.service.config.ControleArquivoConfig;
import com.ricardococati.service.converter.CandlestickConverter;
import com.ricardococati.service.converter.CotacaoConverter;
import com.ricardococati.service.util.ControlaIdArquivoUtil;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BMFCargaCotacaoServiceImplTest {

  @InjectMocks
  private BMFCargaCotacaoServiceImpl target;
  @Mock
  private CandlestickDiarioInserirDAO candlestickDiarioDAO;
  @Mock
  private ControleArquivoConfig arquivoConfig;
  @Mock
  private CandlestickConverter candlestickConverter;
  @Mock
  private CotacaoInserirDAO cotacaoInserirDAO;
  @Mock
  private EmpresaAtivoBuscarDAO empresaAtivoBuscarDAO;
  @Mock
  private CotacaoConverter convertCot;
  @Mock
  private ControlaIdArquivoUtil idArquivoUtil;
  @Mock
  private PostgresEventListener listener;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.service.templates");
  }

  @Test
  public void insereDados() throws Exception {
    //given
    when(candlestickConverter.convert(any())).thenCallRealMethod();
    EmpresaAtivo empresaAtivo = getBuildEmpresaAtivo();
    when(empresaAtivoBuscarDAO.buscaEmpresaAtivo()).thenReturn(Arrays.asList(empresaAtivo));
    when(convertCot.convert(any())).thenCallRealMethod();
    when(idArquivoUtil.getIdentificadorArquivo()).thenReturn(1L);
    //when
    Boolean result = target.insereDados(buildCotacao());
    //then
    assertTrue(result);
  }

  @Test
  public void insereDadosFalse() throws Exception {
    //given
    when(candlestickConverter.convert(any())).thenCallRealMethod();
    EmpresaAtivo empresaAtivo = getBuildEmpresaAtivo();
    empresaAtivo.setAtivo("MGLU");
    when(empresaAtivoBuscarDAO.buscaEmpresaAtivo()).thenReturn(Arrays.asList(empresaAtivo));
    when(convertCot.convert(any())).thenCallRealMethod();
    when(idArquivoUtil.getIdentificadorArquivo()).thenReturn(1L);
    //when
    Boolean result = target.insereDados(buildCotacao());
    //then
    assertFalse(result);
  }

  @Test
  public void insereDadosError() throws Exception {
    //given
    when(candlestickConverter.convert(any())).thenCallRealMethod();
    when(candlestickDiarioDAO.incluirCandlestickDiario(any())).thenThrow(Exception.class);
    EmpresaAtivo empresaAtivo = getBuildEmpresaAtivo();
    when(empresaAtivoBuscarDAO.buscaEmpresaAtivo()).thenReturn(Arrays.asList(empresaAtivo));
    when(convertCot.convert(any())).thenCallRealMethod();
    when(idArquivoUtil.getIdentificadorArquivo()).thenReturn(1L);
    this.thrown.expectMessage("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE");
    this.thrown.expect(Exception.class);
    Cotacao cotacao = buildCotacao();
    //when
    Boolean result = target.insereDados(cotacao);
  }

  private EmpresaAtivo getBuildEmpresaAtivo() {
    return EmpresaAtivo
        .builder()
        .ativo("MGLU3")
        .idEmpresa(1L)
        .build();
  }

  private Cotacao buildCotacao(){
    return from(Cotacao.class).gimme(COTACAO_VALID_001);
  }

}
