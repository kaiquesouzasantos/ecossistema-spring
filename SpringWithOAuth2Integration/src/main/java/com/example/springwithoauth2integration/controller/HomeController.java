package com.example.springwithoauth2integration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> publico() {
        return ResponseEntity.status(200).body("ROTA NAO AUTENTICADA | PUBLICA");
    }

    @GetMapping("/secured")
    public ResponseEntity<String> privado() {
        return ResponseEntity.status(200).body("ROTA AUTENTICADA | PRIVADA");
    }
}
