-- Script a ser usado no SGBD PostgreSQL

-- Criacao do database
CREATE DATABASE banco_poo_prj1;

-- Criacao da tabela
CREATE TABLE construcoes (
	id SERIAL PRIMARY KEY,
	nome varchar(50) NOT NULL,
	endereco varchar(60) NOT NULL,
	tipo varchar(20) NOT NULL,
	data_inicio date NOT NULL,
	data_previsao_termino date NOT NULL,
	area_total_m2 numeric(6,0) NOT NULL,
	orcamento_total numeric (15,2) NOT NULL,
	nome_responsavel varchar(50) NOT NULL,
	status varchar(20) NOT NULL
);

-- Exemplo de linha
INSERT INTO construcoes
	(nome, endereco, tipo,
	 data_inicio, data_previsao_termino,
	 area_total_m2, orcamento_total, nome_responsavel, status)
VALUES
	('Predio Iluminado das luzes', 'Rua Belgica, Pais Ficticia','Residencial',
	'2009-08-01','2009-09-01',
	400, 50000.00, 'Construtora Ficticia Finada','Em andamento')