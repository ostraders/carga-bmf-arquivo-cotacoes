package com.ricardococati.service.impl;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.service.templates.CotacaoTemplateLoader.COTACAO_VALID_001;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.model.entities.Ativo;
import com.ricardococati.model.entities.Cotacao;
import com.ricardococati.repository.dao.CandlestickDiarioInserirDAO;
import com.ricardococati.repository.dao.CotacaoInserirDAO;
import com.ricardococati.repository.dao.AtivoBuscarDAO;
import com.ricardococati.repository.event.CandlestickEventListener;
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
  private AtivoBuscarDAO ativoBuscarDAO;
  @Mock
  private CotacaoConverter convertCot;
  @Mock
  private ControlaIdArquivoUtil idArquivoUtil;
  @Mock
  private CandlestickEventListener listener;
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
    Ativo empresaAtivo = getBuildAtivo();
    when(ativoBuscarDAO.buscaAtivo()).thenReturn(Arrays.asList(empresaAtivo));
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
    Ativo empresaAtivo = getBuildAtivo();
    empresaAtivo.setAtivo("MGLU");
    when(ativoBuscarDAO.buscaAtivo()).thenReturn(Arrays.asList(empresaAtivo));
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
    Ativo empresaAtivo = getBuildAtivo();
    when(ativoBuscarDAO.buscaAtivo()).thenReturn(Arrays.asList(empresaAtivo));
    when(convertCot.convert(any())).thenCallRealMethod();
    when(idArquivoUtil.getIdentificadorArquivo()).thenReturn(1L);
    this.thrown.expectMessage("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE");
    this.thrown.expect(Exception.class);
    Cotacao cotacao = buildCotacao();
    //when
    Boolean result = target.insereDados(cotacao);
  }

  private Ativo getBuildAtivo() {
    return Ativo
        .builder()
        .ativo("MGLU3")
        .idAtivo(1L)
        .build();
  }

  private Cotacao buildCotacao(){
    return from(Cotacao.class).gimme(COTACAO_VALID_001);
  }

}
