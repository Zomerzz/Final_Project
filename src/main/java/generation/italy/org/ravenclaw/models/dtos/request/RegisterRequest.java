package generation.italy.org.ravenclaw.models.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotEmpty(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Il nome deve essere tra 3 e 30 caratteri")
    private String name;
    @NotEmpty(message = "L' Email è obbligatoria")
    @Email(message = "Formato email invalido")
    private String email;
    @NotEmpty(message = "La Password è obbligatoria")
    @Size(min = 5, max = 30, message = "La password deve essere tra 5 e 30 caratteri")
    private String password;

    public RegisterRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
