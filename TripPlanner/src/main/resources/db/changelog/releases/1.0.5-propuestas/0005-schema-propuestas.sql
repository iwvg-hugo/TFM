--liquibase formatted sql

--changeset tripPlanner:0005-schema-Propuestas
CREATE TABLE Propuestas (
    id SERIAL PRIMARY KEY,
    id_viaje INTEGER NOT NULL,
    nombre VARCHAR(255),
    valoracion NUMERIC,
    descripcion TEXT,
    presupuesto NUMERIC,
    ganadora BOOLEAN,
    CONSTRAINT fk_propuesta_viaje FOREIGN KEY (id_viaje) REFERENCES Viajes(id)
);
