package com.miw.tripplanner.utils;

import com.miw.tripplanner.utils.DatabaseContainer;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public abstract class BaseTest {

    @Container
    private static final JdbcDatabaseContainer<?> sqlContainer = DatabaseContainer.getInstance();

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext context;

    @DynamicPropertySource
    static void liquibaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> System.getProperty("spring.datasource.url"));
        registry.add("spring.datasource.username", () -> System.getProperty("spring.datasource.username"));
        registry.add("spring.datasource.password", () -> System.getProperty("spring.datasource.password"));
        registry.add("spring.liquibase.change-log", () -> "classpath:/db/changelog/db.changelog-test.xml");
    }

    @BeforeEach
    void initMockWs() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}