package ee.annjakubel.webshop.model.request.input;

import lombok.Data;

@Data
public class LoginData {
    private String email;
    private String password;
}
