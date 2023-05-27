package springsenderemail.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import springsenderemail.dto.MessageDTO;
import springsenderemail.mapper.MessageHtmlAndFileMapper;
import springsenderemail.mapper.MessageHtmlMapper;
import springsenderemail.mapper.MessageTextMapper;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final JavaMailSender javaMailSender;

    public MessageDTO sendText(MessageDTO messageDTO) {
        javaMailSender.send(new MessageTextMapper().toMapper(messageDTO));
        return messageDTO;
    }

    public MessageDTO sendHtml(MessageDTO messageDTO) throws MessagingException {
        javaMailSender.send(new MessageHtmlMapper().toMapper(messageDTO, javaMailSender));
        return messageDTO;
    }

    public MessageDTO sendMultipartFile(MessageDTO messageDTO) throws MessagingException {
        javaMailSender.send(new MessageHtmlAndFileMapper().toMapper(messageDTO, javaMailSender));
        return messageDTO;
    }
}