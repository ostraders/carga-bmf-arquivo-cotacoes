package com.ricardococati.carga.adapters.repositories;

import java.time.LocalDate;

public interface CalendarioFeriadoDAO {

  Boolean buscaCalendarioFeriado(final LocalDate dataAtual) throws Exception;

}
