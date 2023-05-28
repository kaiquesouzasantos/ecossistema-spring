package com.example.springwithchatgpt.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseModel {
    private String respose;
    private final LocalDateTime time = LocalDateTime.now();
}
