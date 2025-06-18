package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Utente;

public class UtenteNoPassDto {
    private int id;
    private String nome;
    private String email;

    public UtenteNoPassDto(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public static UtenteNoPassDto toDto(Utente u){
        return new UtenteNoPassDto(u.getUtenteId(), u.getNome(), u.getEmail());
    }

    //GETTER & SETTER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
