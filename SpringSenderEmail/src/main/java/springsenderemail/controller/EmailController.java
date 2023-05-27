package springsenderemail.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springsenderemail.dto.MessageDTO;
import springsenderemail.service.MessageService;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class EmailController {
    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> send(@RequestParam(required = false) boolean html, @RequestBody @Valid MessageDTO message) throws MessagingException {
        if(html)
            return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendHtml(message));

        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendText(message));
    }

    @PostMapping(value = "/send/multipart", consumes = "multipart/form-data")
    public ResponseEntity<String> sendMultipart(@ModelAttribute @Valid MessageDTO message) throws MessagingException {
        messageService.sendMultipartFile(message);
        return ResponseEntity.status(HttpStatus.CREATED).body("ENVIADO COM SUCESSO");
    }
}
