package com.ricardococati.carga.usecases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ricardococati.carga.adapters.repositories.HeaderInserirDAO;
import com.ricardococati.carga.config.ControleArquivoConfig;
import com.ricardococati.carga.entities.domains.Header;
import com.ricardococati.carga.usecases.impl.BMFCargaHeaderServiceImpl;
import com.ricardococati.carga.utils.converter.HeaderConverter;
import com.ricardococati.carga.utils.geral.ControlaIdArquivoUtil;
import java.time.LocalDate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BMFCargaHeaderServiceImplTest {

  @InjectMocks
  private BMFCargaHeaderServiceImpl target;
  @Mock
  private ControleArquivoConfig arquivoConfig;
  @Mock
  private HeaderInserirDAO headerInserirDAO;
  @Mock
  private HeaderConverter convertHed;
  @Mock
  private ControlaIdArquivoUtil idArquivoUtil;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void insereDadosTrue() throws Exception {
    //given
    when(convertHed.convert(any())).thenCallRealMethod();
    when(idArquivoUtil.getIdentificadorArquivo()).thenReturn(1L);
    when(headerInserirDAO.incluirHeaderArquivo(any())).thenReturn(Boolean.TRUE);
    //when
    Boolean result = target.insereDados(getBuildHeader());
    //then
    assertTrue(result);
    verify(this.headerInserirDAO, times(1))
        .incluirHeaderArquivo(any());
    verify(this.convertHed, times(1))
        .convert(any());
    verify(this.idArquivoUtil, times(1))
        .getIdentificadorArquivo();
    verify(this.arquivoConfig, times(0))
        .setArquivoValido(any());
  }

  @Test
  public void insereDadosFalse() throws Exception {
    //given
    when(convertHed.convert(any())).thenCallRealMethod();
    when(idArquivoUtil.getIdentificadorArquivo()).thenReturn(1L);
    when(headerInserirDAO.incluirHeaderArquivo(any())).thenReturn(Boolean.FALSE);
    //when
    Boolean result = target.insereDados(getBuildHeader());
    //then
    assertFalse(result);
    verify(this.headerInserirDAO, times(1))
        .incluirHeaderArquivo(any());
    verify(this.convertHed, times(1))
        .convert(any());
    verify(this.idArquivoUtil, times(1))
        .getIdentificadorArquivo();
    verify(this.arquivoConfig, times(0))
        .setArquivoValido(any());
  }

  @Test
  public void insereDadosErrorConvert() throws Exception {
    //given
    when(convertHed.convert(any())).thenThrow(Exception.class);
    when(idArquivoUtil.getIdentificadorArquivo()).thenReturn(1L);
    when(headerInserirDAO.incluirHeaderArquivo(any())).thenReturn(Boolean.FALSE);
    this.thrown.expect(Exception.class);
    this.thrown.expectMessage("OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE");
    //when
    target.insereDados(getBuildHeader());
  }

  @Test
  public void insereDadosIdArquivoUtilNull() throws Exception {
    //given
    when(convertHed.convert(any())).thenCallRealMethod();
    when(idArquivoUtil.getIdentificadorArquivo()).thenReturn(null);
    when(headerInserirDAO.incluirHeaderArquivo(any())).thenReturn(Boolean.FALSE);
    //when
    Boolean result = target.insereDados(getBuildHeader());
    //then
    assertFalse(result);
    verify(this.headerInserirDAO, times(1))
        .incluirHeaderArquivo(any());
    verify(this.convertHed, times(1))
        .convert(any());
    verify(this.idArquivoUtil, times(1))
        .getIdentificadorArquivo();
    verify(this.arquivoConfig, times(0))
        .setArquivoValido(any());
  }

  private Header getBuildHeader() {
    return Header
        .builder()
        .codigoDaOrigem("COD1")
        .dataDaGeracaoDoArquivo(LocalDate.now())
        .nomeDoArquivo("BLAHBLAH")
        .reserva("")
        .tipoRegistro(1L)
        .id("1")
        .build();
  }

}
