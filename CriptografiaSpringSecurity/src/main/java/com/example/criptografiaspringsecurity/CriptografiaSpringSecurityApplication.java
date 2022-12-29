package com.example.criptografiaspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // (exclude = {SecurityAutoConfiguration.class})
// exclude = {<nomeClasse>.class} -> informa para não executar essa classe
public class CriptografiaSpringSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CriptografiaSpringSecurityApplication.class, args);
    }
}
