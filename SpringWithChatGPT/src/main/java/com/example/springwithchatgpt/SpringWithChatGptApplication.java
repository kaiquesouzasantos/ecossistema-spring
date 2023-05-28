package com.example.springwithchatgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringWithChatGptApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWithChatGptApplication.class, args);
    }

}
