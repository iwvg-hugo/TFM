--liquibase formatted sql

--changeset tripPlanner:0008-schema-Tickets
CREATE TABLE Tickets (
    id SERIAL PRIMARY KEY,
    id_plan INTEGER NOT NULL,
    nombre VARCHAR(255),
    asiento VARCHAR(255),
    documento BYTEA,
    qr TEXT,
    CONSTRAINT fk_ticket_plan FOREIGN KEY (id_plan) REFERENCES Planes(id)
);

--changeset tripPlanner:0009-schema-ADD-Columns

ALTER TABLE Tickets
ADD COLUMN categoria VARCHAR(255),
ADD COLUMN descripcion TEXT;
