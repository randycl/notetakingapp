package com.randy_tech.notetakingapp;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test-db")
    public String testDbConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "Database connection successful!";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}
