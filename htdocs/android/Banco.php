CREATE DATABASE androidleonardo;

use androidleonardo;

CREATE TABLE cadastro(
	id INT AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    fone VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    detalhes VARCHAR(100),
    cep VARCHAR(50) NOT NULL,
    cidadeUf VARCHAR(50) NOT NULL,
    endereco VARCHAR(50),
    sexo VARCHAR(50),
    fisica VARCHAR(50),
    auditiva VARCHAR(50),
    visual VARCHAR(50),
    mental VARCHAR(50),

    PRIMARY KEY(id)
);