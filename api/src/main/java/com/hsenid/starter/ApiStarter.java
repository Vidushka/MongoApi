package com.hsenid.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 */

@SpringBootApplication
@ComponentScan("com.hsenid")
public class ApiStarter {
    public static void main(String[] args) {
        SpringApplication.run(ApiStarter.class, args);
    }
}
