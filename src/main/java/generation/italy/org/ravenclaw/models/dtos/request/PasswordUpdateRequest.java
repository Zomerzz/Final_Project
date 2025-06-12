package generation.italy.org.ravenclaw.models.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {

    @NotEmpty(message = "La vecchia password deve è obbligatoria")
    @Size(min = 8, max = 32, message = "La Vecchia password deve essere tra 8 e 32 caratteri")
    private String vecchiaPassword;

    @NotEmpty(message = "La password deve è obbligatoria")
    @Size(min = 8, max = 32, message = "La Vecchia password deve essere tra 8 e 32 caratteri")
    private String newPassword;

    @NotEmpty(message = "La password deve è obbligatoria")
    @Size(min = 8, max = 32, message = "La Vecchia password deve essere tra 8 e 32 caratteri")
    private String newPasswordRepeat;

    public PasswordUpdateRequest(String vecchiaPassword, String newPassword, String newPasswordRepeat) {
        this.vecchiaPassword = vecchiaPassword;
        this.newPassword = newPassword;
        this.newPasswordRepeat = newPasswordRepeat;
    }

    public String getVecchiaPassword() {
        return vecchiaPassword;
    }

    public void setVecchiaPassword(String vecchiaPassword) {
        this.vecchiaPassword = vecchiaPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }
}
