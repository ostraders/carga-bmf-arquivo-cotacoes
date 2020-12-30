-- DROP TABLE SETOR;
CREATE TABLE IF NOT EXISTS SETOR (
	ID_SETOR BIGINT NOT NULL,
	DESCRICAO VARCHAR(255) NULL
);

ALTER TABLE SETOR ADD PRIMARY KEY (ID_SETOR);

CREATE UNIQUE INDEX ON SETOR (DESCRICAO);

CREATE SEQUENCE SETOR_SEQ	START 12;

INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(1, 'Bens Industriais');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(2, 'Consumo Cíclico');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(3, 'Consumo não Cíclico');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(4, 'Financeiro');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(5, 'Materiais Básicos');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(6, 'Outros');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(7, 'Petróleo, Gás e Biocombustíveis');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(8, 'Saúde');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(9, 'Tecnologia da Informação');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(10, 'Telecomunicações');
INSERT INTO SETOR(ID_SETOR, DESCRICAO) VALUES(11, 'Utilidade Pública');