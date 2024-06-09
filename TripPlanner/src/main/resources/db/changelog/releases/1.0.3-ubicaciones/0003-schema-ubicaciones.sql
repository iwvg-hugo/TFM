--liquibase formatted sql

--changeset tripPlanner:0003-schema-Ubicaciones
CREATE TABLE Ubicaciones (
    id SERIAL PRIMARY KEY,
    es_exterior BOOLEAN NOT NULL DEFAULT FALSE,
    tipo_vestimenta TEXT,
    requisitos VARCHAR(255)[]
);
