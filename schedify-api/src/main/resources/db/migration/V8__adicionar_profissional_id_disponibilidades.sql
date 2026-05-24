ALTER TABLE disponibilidades ADD COLUMN IF NOT EXISTS profissional_id BIGINT;

UPDATE disponibilidades SET profissional_id = 1 WHERE profissional_id IS NULL;

ALTER TABLE disponibilidades ALTER COLUMN profissional_id SET NOT NULL;

ALTER TABLE disponibilidades ADD CONSTRAINT fk_disponibilidades_profissional
    FOREIGN KEY (profissional_id) REFERENCES profissionais(id);
