
package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Utente;

public class UtenteDto {
    private int utenteId;
    private String nome;
    private String password;
    private String email;

    public UtenteDto(int utenteId, String nome, String password, String email) {
        this.utenteId = utenteId;
        this.nome = nome;
        this.password = password;
        this.email = email;
    }

    public Utente toUtente(){
        return new Utente(nome, password, email);
    }

    public static UtenteDto toDto(Utente u){
        return new UtenteDto(u.getUtenteId(), u.getNome(), u.getPassword(), u.getEmail());
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
