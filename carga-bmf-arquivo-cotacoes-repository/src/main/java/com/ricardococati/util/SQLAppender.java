package com.ricardococati.util;

import lombok.Getter;

public class SQLAppender {

  @Getter
  private StringBuilder appendSQL;
  @Getter
  private StringBuilder appendSQLSemQuebra;

  public SQLAppender(int capacidade) {
    this.appendSQL = new StringBuilder(capacidade);
    this.appendSQLSemQuebra = new StringBuilder(capacidade);
  }

  public void appendSQL(String texto) {
    final String quebraLinha = System.getProperty("line.separator");
    appendSQL.append(quebraLinha).append(texto);
    appendSQLSemQuebra.append(texto);
  }

}
