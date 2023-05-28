package com.example.springwithchatgpt.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PromptDTO {
    @NotEmpty @NotNull @Size(min = 2, max = 600)
    private String prompt;
}
