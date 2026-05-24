ALTER TABLE agendamentos ADD COLUMN IF NOT EXISTS profissional_id BIGINT;

UPDATE agendamentos SET profissional_id = 1 WHERE profissional_id IS NULL;

ALTER TABLE agendamentos ALTER COLUMN profissional_id SET NOT NULL;

ALTER TABLE agendamentos ADD CONSTRAINT fk_agendamentos_profissional
    FOREIGN KEY (profissional_id) REFERENCES profissionais(id);
