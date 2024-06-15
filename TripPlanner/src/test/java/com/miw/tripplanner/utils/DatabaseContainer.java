package com.miw.tripplanner.utils;

import org.testcontainers.containers.PostgreSQLContainer;

public class DatabaseContainer extends PostgreSQLContainer<DatabaseContainer> {

    private static final String IMAGEN_BD = "postgres:12.10-alpine";
    private static DatabaseContainer contenedor;

    private DatabaseContainer() {
        super(IMAGEN_BD);
    }

    public static DatabaseContainer getInstance() {
        if (null == contenedor) {
            contenedor = new DatabaseContainer();
            contenedor.waitingFor(new SQLValidation("select 1", 120));
        }
        return contenedor;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("spring.datasource.url", getJdbcUrl());
        System.setProperty("spring.datasource.username", getUsername());
        System.setProperty("spring.datasource.password", getPassword());
    }

    @Override
    public void stop() {
        // No aplica
    }
}