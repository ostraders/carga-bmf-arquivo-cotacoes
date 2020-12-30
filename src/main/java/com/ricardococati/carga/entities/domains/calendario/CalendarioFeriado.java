package com.ricardococati.carga.entities.domains.calendario;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarioFeriado {

  private Long idCalendario;
  private LocalDate dataCalendario;
  private String diaSemana;
  private String descricao;

}
