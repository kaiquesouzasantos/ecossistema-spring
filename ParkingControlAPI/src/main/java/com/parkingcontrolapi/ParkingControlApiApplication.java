package com.parkingcontrolapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ParkingControlApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingControlApiApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("senha123"));
    }
}
