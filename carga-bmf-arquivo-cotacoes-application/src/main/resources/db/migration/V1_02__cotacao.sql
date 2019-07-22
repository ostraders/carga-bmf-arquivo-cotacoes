-- Drop Table cotacao;
Create Table cotacao (
	"codbdi" VARCHAR(255) null,
	"codisi" VARCHAR(255) null,
	"codneg" VARCHAR(255) null,
	"datven" VARCHAR(255) null,
	"dismes" VARCHAR(255) null,
	"dtpreg" VARCHAR(255) null,
	"especi" VARCHAR(255) null,
	"fatcot" VARCHAR(255) null,
	"indopc" VARCHAR(255) null,
	"modref" VARCHAR(255) null,
	"nomres" VARCHAR(255) null,
	"prazot" VARCHAR(255) null,
	"preabe" VARCHAR(255) null,
	"preexe" VARCHAR(255) null,
	"premax" VARCHAR(255) null,
	"premed" VARCHAR(255) null,
	"premin" VARCHAR(255) null,
	"preofc" VARCHAR(255) null,
	"preofv" VARCHAR(255) null,
	"preult" VARCHAR(255) null,
	"ptoexe" VARCHAR(255) null,
	"quatot" VARCHAR(255) null,
	"tporeg" VARCHAR(255) null,
	"totneg" VARCHAR(255) null,
	"tpmerc" VARCHAR(255) null,
	"voltot" VARCHAR(255) null
);

ALTER TABLE cotacao ADD PRIMARY KEY (codneg, dtpreg);