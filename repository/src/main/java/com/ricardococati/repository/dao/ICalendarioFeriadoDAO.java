package com.ricardococati.repository.dao;

import java.time.LocalDate;

public interface ICalendarioFeriadoDAO {

  Boolean buscaCalendarioFeriado(final LocalDate dataAtual);

}
