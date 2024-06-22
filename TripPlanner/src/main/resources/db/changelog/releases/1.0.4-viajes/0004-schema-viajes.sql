--liquibase formatted sql

--changeset tripPlanner:0004-schema-Viajes
CREATE TABLE Viajes (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    id_horario INTEGER NOT NULL,
    FOREIGN KEY (id_horario) REFERENCES Horarios(id)
);

--changeset tripPlanner:0004-zviajes images column
ALTER TABLE Viajes ADD COLUMN imagen VARCHAR(255);