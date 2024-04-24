--liquibase formatted sql

--changeset tripPlanner:0001-schema-coreUsers
CREATE TABLE coreUsers (
    id SERIAL NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);