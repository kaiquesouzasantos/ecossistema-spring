package springsenderemail.mapper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;
import springsenderemail.dto.MessageDTO;
import springsenderemail.exception.ExceptionGeneric;

import java.io.File;
import java.io.IOException;

public class MessageHtmlAndFileMapper {
    public MimeMessage toMapper(MessageDTO objetoEntrada, JavaMailSender javaMailSender) throws MessagingException {
        MimeMessage objetoSaida = javaMailSender.createMimeMessage();
        MimeMessageHelper objetoAuxiliar = new MimeMessageHelper(objetoSaida, true);

        objetoAuxiliar.setTo(objetoEntrada.getTo());
        objetoAuxiliar.setSubject(objetoEntrada.getSubject());
        objetoAuxiliar.setText(objetoEntrada.getText(), true); // -> content, <boolean_is_html?>

        objetoAuxiliar.addAttachment(
                objetoEntrada.getMultipart().getOriginalFilename(),
                convertMultipartToFile(objetoEntrada.getMultipart())
        );

        return objetoSaida;
    }

    private File convertMultipartToFile(MultipartFile multipartFile) {
        try {
            File file = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            return file;
        } catch (IOException ignored) {
            throw new ExceptionGeneric("", "", 400);
        }
    }
}
