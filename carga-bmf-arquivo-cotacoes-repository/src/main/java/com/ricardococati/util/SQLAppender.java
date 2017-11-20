package com.ricardococati.util;

public class SQLAppender {

	private StringBuilder appendSQL;
	private StringBuilder appendSQLSemQuebra;

	public SQLAppender(int capacidade) {
		this.appendSQL = new StringBuilder(capacidade);
		this.appendSQLSemQuebra = new StringBuilder(capacidade);
	}

	public StringBuilder getAppendSQL() {
		return appendSQL;
	}

	public void setAppendSQL(StringBuilder appendSQL) {
		this.appendSQL = appendSQL;
	}

	public void appendSQL(String texto) {
		final String quebraLinha = System.getProperty("line.separator");
		appendSQL.append(quebraLinha).append(texto);
		appendSQLSemQuebra.append(texto);
	}

	public StringBuilder getAppendSQLSemQuebra() {
		return appendSQLSemQuebra;
	}

	public void setAppendSQLSemQuebra(StringBuilder appendSQLSemQuebra) {
		this.appendSQLSemQuebra = appendSQLSemQuebra;
	}

}
