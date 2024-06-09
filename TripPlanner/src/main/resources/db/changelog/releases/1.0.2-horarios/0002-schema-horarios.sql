--liquibase formatted sql

--changeset tripPlanner:0002-schema-Horarios
CREATE TABLE Horarios (
    id SERIAL PRIMARY KEY,
    entrada TIME,
    salida TIME
);