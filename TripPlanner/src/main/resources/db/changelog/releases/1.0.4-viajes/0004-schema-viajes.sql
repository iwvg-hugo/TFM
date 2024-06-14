--liquibase formatted sql

--changeset tripPlanner:0004-schema-Viajes
CREATE TABLE Viajes (
    id SERIAL PRIMARY KEY,
    id_horario INTEGER NOT NULL,
    FOREIGN KEY (id_horario) REFERENCES Horarios(id)
);
