--liquibase formatted sql

--changeset tripPlanner:0006-schema-Pagos
CREATE TABLE Pagos
(
    id    SERIAL PRIMARY KEY,
    total NUMERIC
);

--changeset tripPlanner:0007. alter pagos
ALTER TABLE Pagos
    ADD COLUMN descripcion text;

ALTER TABLE Pagos
    ADD COLUMN id_horario INTEGER;

ALTER TABLE Pagos
    ADD CONSTRAINT fk_horario
        FOREIGN KEY (id_horario)
            REFERENCES Horarios (id);

