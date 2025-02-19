package org.spring.postgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootPostgreSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPostgreSqlApplication.class, args);
    }

    @RequestMapping
    public String welcome() {
        return "Welcome to PostgreSQL";
    }

}
