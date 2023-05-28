package com.example.springwithchatgpt.service;

import com.example.springwithchatgpt.dto.PromptDTO;
import com.example.springwithchatgpt.exception.ExceptionGeneric;
import com.example.springwithchatgpt.model.ResponseModel;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import retrofit2.HttpException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatGPTService {
    @Value("${chat.gpt.api.key}") private String API_KEY;
    @Value("${chat.gpt.api.model}") private String API_MODEL;

    @Retryable(value = HttpException.class, maxAttempts = 5, backoff = @Backoff(delay = 500))
    public ResponseModel response(PromptDTO promptDTO) {
        return ResponseModel.builder()
                .respose(
                    new OpenAiService(API_KEY).createCompletion(
                        CompletionRequest
                                .builder()
                                .model(API_MODEL)
                                .prompt(promptDTO.getPrompt())
                                .maxTokens(2000)
                                .build()
                    )
                        .getChoices()
                        .stream()
                        .map(CompletionChoice::getText)
                        .collect(Collectors.joining())
                            .replace("\n", "")
                ).build();
    }

    @Recover
    public ResponseModel responseFailed(PromptDTO promptDTO) {
        throw new ExceptionGeneric("REQUEST FAILED", "THE SERVER IS UNDER REQUESTS OVERLOAD", 500);
    }
}