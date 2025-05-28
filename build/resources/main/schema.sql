
CREATE TABLE IF NOT EXISTS setores(
    id                     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                   VARCHAR(100) NOT NULL,
    precoBase              DOUBLE NOT NULL,
    capacidadeMaxima       INT NOT NULL,
    horarioAbre            TIME NOT NULL,
    horarioFecha           TIME NOT NULL,
    limiteDuracao          INT NOT NULL,
    ocupacaoAtual          INT DEFAULT 0,
    estaFechado            BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS veiculos(
    placa VARCHAR(20) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS vagas(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    idSetor   BIGINT NOT NULL,
    latitude  DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    ocupada   BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_vaga_setor FOREIGN KEY (idSetor) REFERENCES setores (id)
);

CREATE TABLE IF NOT EXISTS sessaoEstacionamento(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    placa             VARCHAR(20) NOT NULL,
    idVaga            BIGINT NOT NULL,
    idSetor           BIGINT NOT NULL,
    hrEntrada         DATETIME NOT NULL,
    hrEstacionamento  DATETIME,
    hrSaida           DATETIME,
    preco             DOUBLE,
    precoBase         DOUBLE NOT NULL DEFAULT 0,
    ativa             BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_sessao_veiculo FOREIGN KEY (placa) REFERENCES veiculos (placa),
    CONSTRAINT fk_sessao_vaga FOREIGN KEY (idVaga) REFERENCES vagas (id),
    CONSTRAINT fk_sessao_setor FOREIGN KEY (idSetor) REFERENCES setores (id)
);

CREATE TABLE IF NOT EXISTS faturamentos(
    id    VARCHAR(36) PRIMARY KEY,
    setor VARCHAR(100) NOT NULL,
    data  DATE NOT NULL,
    valor DOUBLE NOT NULL
);
