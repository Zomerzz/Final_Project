package generation.italy.org.ravenclaw.models.dtos.response;

import generation.italy.org.ravenclaw.models.entities.Authority;

import java.util.List;

public class UtenteResponse {
    private int id;
    private String nome;
    private String email;
    private String password;
    private List<Authority> authorities;

    public UtenteResponse(int id, String nome, String email, List<Authority> authorities) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.authorities = authorities;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
