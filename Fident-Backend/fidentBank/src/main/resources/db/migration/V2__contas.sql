CREATE TABLE tb_conta(
    id TEXT PRIMARY KEY NOT NULL UNIQUE,
    numero_conta VARCHAR(20) NOT NULL UNIQUE,
    saldo DECIMAL(15, 2) NOT NULL,
    limite DECIMAL(15, 2),
    cliente_id TEXT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES tb_usuario(id)
);