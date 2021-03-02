package com.ricardococati.carga.usecases.batchprocess.writer;

import com.ricardococati.carga.config.ControleArquivoConfig;
import com.ricardococati.carga.entities.domains.arquivo.Arquivo;
import com.ricardococati.carga.entities.domains.cotacao.Cotacao;
import com.ricardococati.carga.entities.domains.header.Header;
import com.ricardococati.carga.usecases.cotacao.CargaCotacaoService;
import com.ricardococati.carga.usecases.header.CargaHeaderService;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
@RequiredArgsConstructor
public class CargaItemWriter implements ItemWriter<Arquivo> {

  private static final String MENSAGEM_ERRO = "OCORREU UM ERRO NA ESCRITA DOS DADOS NA BASE - write - Erro: {}";
  private final CargaCotacaoService cargaCotacaoService;
  private final CargaHeaderService cargaHeaderService;
  private final ControleArquivoConfig arquivoConfig;

  @Override
  public void write(List<? extends Arquivo> listDTOs) {
    try {
      listDTOs
          .stream()
          .filter(Objects::nonNull)
          .forEach(bmfCargaDTO -> {
            if (Header.class.isInstance(bmfCargaDTO)) {
              insereHeader((Header) bmfCargaDTO);
            } else if (Cotacao.class.isInstance(bmfCargaDTO)) {
              insereCotacao((Cotacao) bmfCargaDTO);
            }
          });
    } catch (Exception e) {
      arquivoConfig.setArquivoValido(false);
      log.error(MENSAGEM_ERRO, e.getMessage());
    }
  }

  private void insereCotacao(final Cotacao bmfCargaDTO) {
    final Cotacao cotacao = bmfCargaDTO;
    try {
      cargaCotacaoService.insereDados(cotacao);
    } catch (Exception e) {
      log.error(MENSAGEM_ERRO, e.getMessage());
    }
  }

  private void insereHeader(final Header bmfCargaDTO) {
    try {
      cargaHeaderService.insereDados(bmfCargaDTO);
    } catch (Exception e) {
      log.error(MENSAGEM_ERRO, e.getMessage());
    }
  }

}
