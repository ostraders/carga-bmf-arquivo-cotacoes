package com.ricardococati.repository.dao.impl;

import com.ricardococati.model.entities.Ativo;
import com.ricardococati.repository.dao.BaseJdbcTest;
import com.ricardococati.repository.dao.mapper.AtivoMapper;
import com.ricardococati.repository.dao.sqlutil.AtivoSQLUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AtivoBuscarDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private AtivoBuscarDAOImpl target;
  @Mock
  private AtivoSQLUtil sqlUtil;
  @Mock
  private AtivoMapper mapper;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    target = new AtivoBuscarDAOImpl(
        getNamedParameterJdbcTemplate(),
        sqlUtil,
        mapper
    );
  }

  @Test
  public void buscaAtivoPrimeiro() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<Ativo> result = target.buscaAtivo();
    //then
    assertFalse(result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(342);
    assertThat(result.get(0).getAtivo()).isNotNull().isEqualTo("AALR3");
    assertThat(result.get(0).getIdAtivo()).isNotNull().isEqualTo(235L);
  }

  @Test
  public void buscaAtivoContemBOEI34() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<Ativo> result = target.buscaAtivo();
    //then
    assertFalse(result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(342);
    assertThat(result.get(44).getAtivo()).isNotNull().isEqualTo("BOEI34");
    assertThat(result.get(44).getIdAtivo()).isNotNull().isEqualTo(9L);
  }

  @Test
  public void buscaAtivoContemLCAM3() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<Ativo> result = target.buscaAtivo();
    //then
    assertFalse(result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(342);
    assertThat(result.get(197).getAtivo()).isNotNull().isEqualTo("LCAM3");
    assertThat(result.get(197).getIdAtivo()).isNotNull().isEqualTo(63L);
  }

  @Test
  public void buscaAtivoContemNEMO3() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<Ativo> result = target.buscaAtivo();
    //then
    assertFalse(result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(342);
    assertThat(result.get(228).getAtivo()).isNotNull().isEqualTo("NEMO3");
    assertThat(result.get(228).getIdAtivo()).isNotNull().isEqualTo(193L);
  }

  @Test
  public void buscaAtivoNull() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenReturn(", ");
    when(mapper.mapper(any())).thenCallRealMethod();
    this.thrown.expectMessage("Erro na execução do método ATIVO");
    this.thrown.expect(Exception.class);
    //when
    List<Ativo> result = target.buscaAtivo();
  }

}
