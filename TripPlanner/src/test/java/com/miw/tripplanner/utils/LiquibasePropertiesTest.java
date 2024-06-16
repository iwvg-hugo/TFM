package com.miw.tripplanner.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LiquibasePropertiesTest extends BaseTest {

    @Value("${spring.liquibase.change-log}")
    private String changeLog;


    @Test
    void testLiquibaseChangeLog() {
        System.out.println("ChangeLog path: " + changeLog);
        assertEquals("classpath:/db/changelog/db.changelog-test.xml", changeLog);
    }
}