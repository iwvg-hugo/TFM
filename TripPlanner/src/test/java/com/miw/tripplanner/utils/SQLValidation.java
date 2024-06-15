package com.miw.tripplanner.utils;


import java.sql.Connection;
import java.sql.Statement;

import org.testcontainers.containers.ContainerLaunchException;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.JdbcDatabaseContainer.NoDriverFoundException;
import org.testcontainers.containers.wait.strategy.AbstractWaitStrategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SQLValidation extends AbstractWaitStrategy {


    private final String consulta;

    private final Integer timeout;

    public SQLValidation(String consulta, Integer timeout) {
        this.consulta = consulta;
        this.timeout = timeout;
    }


    @Override
    protected void waitUntilReady() {
        JdbcDatabaseContainer<?> container = (JdbcDatabaseContainer<?>) this.waitStrategyTarget;

        try {
            tryConnection(container);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ContainerLaunchException("Arranque interrumpido");
        }

        log.info("Contenedor lanzado (JDBC URL: {})", container.getJdbcUrl());
    }

    protected void tryConnection(JdbcDatabaseContainer<?> container) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        synchronized (this) {
            while (System.currentTimeMillis() < startTime + (1000L * timeout)) {
                try {
                    if (!container.isRunning() || testQuery(container)) {
                        break;
                    }
                } catch (NoDriverFoundException e) {
                    throw e;
                } catch (Exception e) {
                    log.debug("Error al lanzar la query:", e);
                }
                wait(500L);
            }
        }
    }

    protected boolean testQuery(JdbcDatabaseContainer<?> container) {
        try (Connection connection = container.createConnection("");
             Statement statement = connection.createStatement()) {
            return statement.execute(consulta);
        } catch (Exception e) {
            log.debug("Error al lanzar la query:", e);
            return false;
        }
    }
}