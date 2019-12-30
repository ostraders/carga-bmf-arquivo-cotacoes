package com.ricardococati.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.ricardococati.repository.dao.CalendarioFeriadoDAO;
import com.ricardococati.service.DescompactarArquivoService;
import com.ricardococati.service.DownloadArquivoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BaixarArquivoServiceTest {

  @InjectMocks
  private BaixarEDescompactaArquivoServiceImpl target;
  @Mock
  private CalendarioFeriadoDAO feriadoDAO;
  @Mock
  private DescompactarArquivoService descompactarService;
  @Mock
  private DownloadArquivoService downloadService;
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void baixaArquivoCotacaoTrue() throws Exception {
    //given
    when(descompactarService.descompactaArquivoCotacao(any())).thenReturn(Boolean.TRUE);
    when(downloadService.doanloadArquivo(any(), any())).thenReturn(Boolean.TRUE);
    when(feriadoDAO.buscaCalendarioFeriado(any())).thenReturn(Boolean.FALSE);
    //when
    Boolean returned = target.baixaEDescompactaArquivoCotacao();
    //then
    assertThat(returned).isNotNull().isTrue();
  }

  @Test
  public void baixaArquivoCotacaoFalse() throws Exception {
    //given
    when(descompactarService.descompactaArquivoCotacao(any())).thenReturn(Boolean.TRUE);
    when(downloadService.doanloadArquivo(any(), any())).thenReturn(Boolean.TRUE);
    when(feriadoDAO.buscaCalendarioFeriado(any())).thenReturn(Boolean.TRUE);
    //when
    Boolean returned = target.baixaEDescompactaArquivoCotacao();
    //then
    assertThat(returned).isNotNull().isFalse();
  }

  @Test
  public void baixaArquivoCotacaoError() throws Exception {
    //given
    when(descompactarService.descompactaArquivoCotacao(any())).thenReturn(Boolean.TRUE);
    when(downloadService.doanloadArquivo(any(), any())).thenReturn(Boolean.TRUE);
    when(feriadoDAO.buscaCalendarioFeriado(any())).thenReturn(null);

    this.thrown.expect(Exception.class);
    this.thrown.expectMessage("Erro ao obter data dia util");

    //when
    Boolean returned = target.baixaEDescompactaArquivoCotacao();
  }
}