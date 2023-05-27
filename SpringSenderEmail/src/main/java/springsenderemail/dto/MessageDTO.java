package springsenderemail.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class MessageDTO {
    @NotNull @NotEmpty @Email
    String to;

    @NotNull @NotEmpty
    String subject, text;

    MultipartFile multipart;
}
