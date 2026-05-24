CREATE TABLE IF NOT EXISTS servico_profissional (
    servico_id BIGINT NOT NULL REFERENCES servicos(id),
    profissional_id BIGINT NOT NULL REFERENCES profissionais(id),
    PRIMARY KEY (servico_id, profissional_id)
);
