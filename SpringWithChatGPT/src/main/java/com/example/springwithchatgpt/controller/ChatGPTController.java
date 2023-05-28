package com.example.springwithchatgpt.controller;

import com.example.springwithchatgpt.dto.PromptDTO;
import com.example.springwithchatgpt.model.ResponseModel;
import com.example.springwithchatgpt.service.ChatGPTService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatGPTController {
    private final ChatGPTService chatGPTService;

    @PostMapping("/chat")
    public ResponseEntity<ResponseModel> send(@RequestBody @Valid PromptDTO prompt) {
        return ResponseEntity.status(HttpStatus.OK).body(chatGPTService.response(prompt));
    }
}
