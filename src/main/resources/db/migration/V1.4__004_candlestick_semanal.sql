-- DROP TABLE CANDLESTICK_SEMANAL;
CREATE TABLE IF NOT EXISTS CANDLESTICK_SEMANAL (
	ID_CANDLE_SEMANAL BIGINT NOT NULL,
	CODNEG VARCHAR(255) NULL,
	DTPREGFIM DATE NULL,
	DTPREGINI DATE NULL,
	PREABE DECIMAL(14,2) NULL,
	PREMAX DECIMAL(14,2) NULL,
	PREMIN DECIMAL(14,2) NULL,
	PREULT DECIMAL(14,2) NULL,
	SEMANA INTEGER NULL,
	VOLTOT DECIMAL(20,2) NULL
);

ALTER TABLE CANDLESTICK_SEMANAL ADD PRIMARY KEY (CODNEG, DTPREGINI, DTPREGFIM);

CREATE SEQUENCE CANDLESTICK_SEMANAL_SEQ	START 1;

-- Permissions
--ALTER SEQUENCE candlestick_semanal_seq OWNER TO dbbmf;
--GRANT ALL ON SEQUENCE candlestick_semanal_seq TO dbbmf;