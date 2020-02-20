package com.ricardococati.repository.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.ricardococati.model.entities.EmpresaAtivo;
import com.ricardococati.repository.dao.BaseJdbcTest;
import com.ricardococati.repository.dao.mapper.EmpresaAtivoMapper;
import com.ricardococati.repository.dao.sqlutil.EmpresaAtivoSQLUtil;
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
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmpresaAtivoBuscarDAOImplTest extends BaseJdbcTest {

  @InjectMocks
  private EmpresaAtivoBuscarDAOImpl target;
  @Mock
  private EmpresaAtivoSQLUtil sqlUtil;
  @Mock
  private EmpresaAtivoMapper mapper;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    target = new EmpresaAtivoBuscarDAOImpl(
        getNamedParameterJdbcTemplate(),
        sqlUtil,
        mapper
    );
  }

  @Test
  public void buscaEmpresaAtivoPrimeiro() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<EmpresaAtivo> result = target.buscaEmpresaAtivo();
    //then
    assertFalse(result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(315);
    assertThat(result.get(0).getAtivo()).isNotNull().isEqualTo("AALR3");
    assertThat(result.get(0).getIdEmpresa()).isNotNull().isEqualTo(192L);
  }

  @Test
  public void buscaEmpresaAtivoContemBPAN4() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<EmpresaAtivo> result = target.buscaEmpresaAtivo();
    //then
    assertFalse(result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(315);
    assertThat(result.get(44).getAtivo()).isNotNull().isEqualTo("BPAN4");
    assertThat(result.get(44).getIdEmpresa()).isNotNull().isEqualTo(147L);
  }

  @Test
  public void buscaEmpresaAtivoContemMGLU3() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<EmpresaAtivo> result = target.buscaEmpresaAtivo();
    //then
    assertFalse(result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(315);
    assertThat(result.get(197).getAtivo()).isNotNull().isEqualTo("MGLU3");
    assertThat(result.get(197).getIdEmpresa()).isNotNull().isEqualTo(73L);
  }

  @Test
  public void buscaEmpresaAtivoContemPETR3() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenCallRealMethod();
    when(mapper.mapper(any())).thenCallRealMethod();
    //when
    List<EmpresaAtivo> result = target.buscaEmpresaAtivo();
    //then
    assertFalse(result.isEmpty());
    assertThat(result).isNotNull().size().isEqualTo(315);
    assertThat(result.get(228).getAtivo()).isNotNull().isEqualTo("PETR3");
    assertThat(result.get(228).getIdEmpresa()).isNotNull().isEqualTo(182L);
  }

  @Test
  public void buscaEmpresaAtivoNull() throws Exception {
    //given
    when(sqlUtil.getSelectAtivos()).thenReturn(", ");
    when(mapper.mapper(any())).thenCallRealMethod();
    this.thrown.expectMessage("Erro na execução do método EMPRESA_ATIVO");
    this.thrown.expect(Exception.class);
    //when
    List<EmpresaAtivo> result = target.buscaEmpresaAtivo();
  }

}
