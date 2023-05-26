package springsenderemail.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionGeneric extends RuntimeException {
    private final String title, message;
    private final int status;

    public ResponseBody getCorpoResposta(){
        return new ResponseBody(title, message, status);
    }
}
