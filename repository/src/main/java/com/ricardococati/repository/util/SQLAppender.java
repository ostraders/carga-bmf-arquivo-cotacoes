package com.ricardococati.repository.util;

import lombok.Getter;

public class SQLAppender {

  @Getter
  private StringBuilder appendSQL;
  @Getter
  private StringBuilder appendSQLSemQuebra;

  public SQLAppender(int capacidadeInicial) {
    this.appendSQL = new StringBuilder(capacidadeInicial);
    this.appendSQLSemQuebra = new StringBuilder(capacidadeInicial);
  }

  public void appendSQL(String texto) {
    final String quebraLinha = System.getProperty("line.separator");
    appendSQL.append(quebraLinha).append(texto);
    appendSQLSemQuebra.append(texto);
  }

}
