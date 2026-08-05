CREATE TABLE tb_conta(
    id BIGSERIAL PRIMARY KEY,
    numero_conta VARCHAR(20) NOT NULL UNIQUE,
    saldo DECIMAL(15, 2) NOT NULL,
    limite DECIMAL(15, 2),
    cliente_id BIGINT NOT NULL,
    atibo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);