package com.shamimzariwala.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShamimZariWalaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShamimZariWalaBackendApplication.class, args);
    }

}
