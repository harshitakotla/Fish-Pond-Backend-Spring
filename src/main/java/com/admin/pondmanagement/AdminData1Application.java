package com.admin.pondmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AdminData1Application {
    public static void main(String[] args) {
        SpringApplication.run(AdminData1Application.class, args);
    }
}
