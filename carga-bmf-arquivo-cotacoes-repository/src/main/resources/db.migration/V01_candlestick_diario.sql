-- Drop Table candlestick_diario;
create table candlestick_diario (
	"codneg" VARCHAR(255) null,
	"dtpreg" timestamp null,
	"media_movel_gerada" BOOLEAN null,
	"preco_abertura" VARCHAR(255) null,
	"preco_max" VARCHAR(255) null,
	"preco_min" VARCHAR(255) null,
	"preco_fechamento" VARCHAR(255) null,
	"semana" INTEGER null,
	"semana_gerada" BOOLEAN null,
	"volume_total" VARCHAR(255) null
);

ALTER TABLE candlestick_diario ADD PRIMARY KEY (codneg, dtpreg);