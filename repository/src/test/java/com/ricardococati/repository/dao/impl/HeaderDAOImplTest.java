package com.ricardococati.repository.dao.impl;

import static br.com.six2six.fixturefactory.Fixture.from;
import static com.ricardococati.repository.dao.templates.HeaderDTOTemplateLoader.HEADER_DTO_VALID_001;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.ricardococati.model.dto.CotacaoDTO;
import com.ricardococati.model.dto.HeaderDTO;
import com.ricardococati.repository.dao.BaseJdbcTest;
import com.ricardococati.repository.dao.sqlutil.HeaderSQLUtil;
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
public class HeaderDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private HeaderDAOImpl target;
  @Mock
  private HeaderSQLUtil sqlUtil;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    FixtureFactoryLoader.loadTemplates("com.ricardococati.repository.dao.templates");
    target = new HeaderDAOImpl(getNamedParameterJdbcTemplate(), sqlUtil);
  }

  @Test
  public void incluirHeaderArquivo() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    HeaderDTO dto = buildHeaderDTO();
    dto.setIdentificacaoArquivo(1L);
    //when
    Boolean retorno = target.incluirHeaderArquivo(dto);
    //then
    assertTrue(retorno);
  }

  @Test
  public void incluirHeaderNull() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    this.thrown.expectMessage("Violação de chave na inserção de HEADER_ARQ");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirHeaderArquivo(null);
  }

  @Test
  public void incluirHeaderIdentificacaoArquivoNull() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    HeaderDTO dto = buildHeaderDTO();
    dto.setIdentificacaoArquivo(null);
    this.thrown.expectMessage("Violação de chave na inserção de HEADER_ARQ");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirHeaderArquivo(dto);
  }

  @Test
  public void incluirHeaderDataDaGeracaoDoArquivoNull() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    HeaderDTO dto = buildHeaderDTO();
    dto.setIdentificacaoArquivo(1L);
    dto.setDataDaGeracaoDoArquivo(null);
    this.thrown.expectMessage("Violação de chave na inserção de HEADER_ARQ");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirHeaderArquivo(dto);
  }

  @Test
  public void incluirHeaderNomeDoArquivoNull() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenCallRealMethod();
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    HeaderDTO dto = buildHeaderDTO();
    dto.setIdentificacaoArquivo(1L);
    dto.setNomeDoArquivo(null);
    this.thrown.expectMessage("Violação de chave na inserção de HEADER_ARQ");
    this.thrown.expect(DataIntegrityViolationException.class);
    //when
    Boolean retorno = target.incluirHeaderArquivo(dto);
  }

  @Test
  public void incluirCotacaoInvalido() throws Exception {
    //given
    when(sqlUtil.getInsert()).thenReturn(",");
    when(sqlUtil.toParameters(any())).thenCallRealMethod();
    HeaderDTO dto = buildHeaderDTO();
    dto.setIdentificacaoArquivo(1L);
    this.thrown.expectMessage("Erro na execução do método HEADER_ARQ");
    this.thrown.expect(Exception.class);
    //when
    Boolean retorno = target.incluirHeaderArquivo(dto);
  }

  private HeaderDTO buildHeaderDTO(){
    return from(HeaderDTO.class).gimme(HEADER_DTO_VALID_001);
  }

}
