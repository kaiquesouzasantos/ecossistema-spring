package springsenderemail.mapper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import springsenderemail.dto.MessageDTO;

public class MessageHtmlMapper {
    public MimeMessage toMapper(MessageDTO objetoEntrada, JavaMailSender javaMailSender) throws MessagingException {
        MimeMessage objetoSaida = javaMailSender.createMimeMessage();
        MimeMessageHelper objetoAuxiliar = new MimeMessageHelper(objetoSaida, true);

        objetoAuxiliar.setTo(objetoEntrada.getTo());
        objetoAuxiliar.setSubject(objetoEntrada.getSubject());
        objetoAuxiliar.setText(objetoEntrada.getText(), true); // -> content, <boolean_is_html?>

        return objetoSaida;
    }
}