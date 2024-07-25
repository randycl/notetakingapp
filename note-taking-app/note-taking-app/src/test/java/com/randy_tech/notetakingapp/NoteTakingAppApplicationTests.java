package com.randy_tech.notetakingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteTakingAppApplicationTests {

	@Test
	void contextLoads() {
	}
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public String testDbConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "Database connection successful!";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}
