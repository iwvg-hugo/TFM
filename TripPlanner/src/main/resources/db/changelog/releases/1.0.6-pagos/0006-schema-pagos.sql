--liquibase formatted sql

--changeset tripPlanner:0006-schema-Pagos
CREATE TABLE Pagos (
    id SERIAL PRIMARY KEY,
    total NUMERIC
);
