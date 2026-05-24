CREATE TABLE IF NOT EXISTS bloqueios (
    id BIGSERIAL PRIMARY KEY,
    profissional_id BIGINT NOT NULL REFERENCES profissionais(id),
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    motivo VARCHAR(500) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_bloqueios_profissional_periodo
    ON bloqueios(profissional_id, data_inicio, data_fim);
