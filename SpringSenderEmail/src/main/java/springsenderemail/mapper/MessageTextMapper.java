package springsenderemail.mapper;

import org.springframework.mail.SimpleMailMessage;
import springsenderemail.dto.MessageDTO;

public class MessageTextMapper {
    public SimpleMailMessage toMapper(MessageDTO objetoEntrada) {
        SimpleMailMessage objetoSaida = new SimpleMailMessage();

        objetoSaida.setTo(objetoEntrada.getTo());
        objetoSaida.setSubject(objetoEntrada.getSubject());
        objetoSaida.setText(objetoEntrada.getText());

        return objetoSaida;
    }
}