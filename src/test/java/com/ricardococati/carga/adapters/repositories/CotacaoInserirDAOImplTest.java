package com.ricardococati.carga.adapters.repositories;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.carga.templates.CotacaoDTOTemplateLoader.COTACAO_DTO_VALID_001;
import static com.ricardococati.carga.templates.HeaderDTOTemplateLoader.HEADER_DTO_VALID_001;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.carga.adapters.repositories.cotacao.impl.CotacaoInserirDAOImpl;
import com.ricardococati.carga.config.BaseJdbcTest;
import com.ricardococati.carga.adapters.repositories.cotacao.sqlutil.CotacaoSQLUtil;
import com.ricardococati.carga.adapters.repositories.header.sqlutil.HeaderSQLUtil;
import com.ricardococati.carga.utils.InserirDadosPrimariosCotacaoUtil;
import com.ricardococati.carga.entities.domains.cotacao.dto.CotacaoDTO;
import com.ricardococati.carga.entities.domains.header.dto.HeaderDTO;
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
public class CotacaoInserirDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private CotacaoInserirDAOImpl target;
  @Mock
  private CotacaoSQLUtil sqlUtil;
  @Mock
  private HeaderSQLUtil headerSQLUtil;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.carga.templates");
    target = new CotacaoInserirDAOImpl(
            getNamedParameterJdbcTemplate(),
            sqlUtil
    );
    InserirDadosPrimariosCotacaoUtil util = new InserirDadosPrimariosCotacaoUtil(
            buildHeader(),
            getNamedParameterJdbcTemplate(),
            headerSQLUtil
    );
    util.incluiHeaderAntesDeExecutarTestes();
  }

  @Test
  public void incluirCotacao() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CotacaoDTO dto = buildCotacaoDTO();
    dto.setIdentificacaoArquivo(1L);
    //when
    Boolean retorno = target.incluirCotacao(dto);
    //then
    assertTrue(retorno);
  }

  @Test
  public void incluirCotacaoNull() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na inserção de COTACAO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCotacao(null);
  }

  @Test
  public void incluirCotacaoCodnegNull() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CotacaoDTO dto = buildCotacaoDTO();
    dto.setIdentificacaoArquivo(1L);
    dto.setCodneg(null);
    this.thrown.expectMessage("Violação de chave na inserção de COTACAO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCotacao(dto);
  }

  @Test
  public void incluirCotacaoDtpregNull() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CotacaoDTO dto = buildCotacaoDTO();
    dto.setIdentificacaoArquivo(1L);
    dto.setDtpreg(null);
    this.thrown.expectMessage("Violação de chave na inserção de COTACAO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCotacao(dto);
  }

  @Test
  public void incluirCotacaoIdentificacaoArquivoNull() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CotacaoDTO dto = buildCotacaoDTO();
    dto.setIdentificacaoArquivo(null);
    this.thrown.expectMessage("Violação de chave na inserção de COTACAO");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirCotacao(dto);
  }

  @Test
  public void incluirCotacaoInvalido() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenReturn(",");
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    CotacaoDTO dto = buildCotacaoDTO();
    dto.setIdentificacaoArquivo(1L);
    this.thrown.expectMessage("Erro na execução do método COTACAO");
    this.thrown.expect(Exception.class);
    //when
    Boolean retorno = target.incluirCotacao(dto);
  }

  private CotacaoDTO buildCotacaoDTO(){
    return from(CotacaoDTO.class).gimme(COTACAO_DTO_VALID_001);
  }

  private HeaderDTO buildHeader(){
    return from(HeaderDTO.class).gimme(HEADER_DTO_VALID_001);
  }

}
