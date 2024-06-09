--liquibase formatted sql

--changeset tripPlanner:0004-schema-Viajes
CREATE TABLE Viajes (
    id SERIAL PRIMARY KEY
);
