ALTER TABLE header_arq DROP CONSTRAINT IF EXISTS header_arq_pkey;
ALTER TABLE cotacao DROP CONSTRAINT IF EXISTS fk_cotacao_header;
ALTER TABLE candlestick_diario DROP CONSTRAINT IF EXISTS fk_candlestick_diario_cotacao;
ALTER TABLE candlestick_semanal DROP CONSTRAINT IF EXISTS fk_candlestick_semanal_candlestick;
