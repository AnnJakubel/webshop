package ee.annjakubel.webshop.controller.exception;

import org.springframework.security.authentication.AuthenticationManager;


public class AuthenticationException extends Throwable {

    public AuthenticationException(String message) {
        super(message);
    }

}
