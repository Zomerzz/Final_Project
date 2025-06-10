package generation.italy.org.ravenclaw.models.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {
    @NotEmpty(message = "L' Email è obbligatoria")
    @Email(message = "Formato del email invalido")
    private String email;

    @NotEmpty(message = "La Password è obbligatoria")
    @Size(min = 5, max = 30, message = "Password deve essere tra 5 e 30 caratteri")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
