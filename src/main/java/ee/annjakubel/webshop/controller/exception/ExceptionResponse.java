package ee.annjakubel.webshop.controller.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ExceptionResponse {
    private int HttpStatusCode;
    private HttpStatus httpStatus;
    private Date timestamp;
    private String message;
}
