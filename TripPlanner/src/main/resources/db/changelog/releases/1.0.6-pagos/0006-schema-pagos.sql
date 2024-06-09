--liquibase formatted sql

--changeset tripPlanner:0006-schema-Pagos
CREATE TABLE Pagos (
    id SERIAL PRIMARY KEY,
    id_pagador INTEGER,
    total NUMERIC
);
