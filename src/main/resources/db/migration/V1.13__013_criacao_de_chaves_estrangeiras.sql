ALTER TABLE header_arq ADD PRIMARY KEY (identificacao_reg);

ALTER TABLE cotacao
ADD CONSTRAINT fk_cotacao_header
FOREIGN KEY (identificacao_reg) REFERENCES header_arq (identificacao_reg);

ALTER TABLE candlestick_diario
ADD CONSTRAINT fk_candlestick_diario_cotacao
FOREIGN KEY (codneg, dtpreg) REFERENCES cotacao (codneg, dtpreg);

ALTER TABLE candlestick_semanal
ADD CONSTRAINT fk_candlestick_semanal_candlestick
FOREIGN KEY (codneg, dtpregini) REFERENCES candlestick_diario (codneg, dtpreg);
