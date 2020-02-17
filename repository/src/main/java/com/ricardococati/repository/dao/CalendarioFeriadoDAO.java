package com.ricardococati.repository.dao;

import java.time.LocalDate;

public interface CalendarioFeriadoDAO {

  Boolean buscaCalendarioFeriado(final LocalDate dataAtual) throws Exception;

}
