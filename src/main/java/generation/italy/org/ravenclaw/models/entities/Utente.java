package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente implements UserDetails {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "utente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int utenteId;
    private String nome;
    private String password;
    private String email;

    // === ONE TO MANY ===

    @OneToMany(mappedBy = "utente")
    private List<VideogiocoGiocato> videogiochiGiocati = new ArrayList<>();

    @OneToMany(mappedBy = "utente")
    private List<LibroLetto> libriLetti = new ArrayList<>();

    @OneToMany(mappedBy = "utente")
    private List<FilmVisto> filmVisti = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "utenti_authorities", joinColumns = @JoinColumn(name = "utente_id"))
    private List<Authority> authorities;

    // === CONSTRUCTORS ===

    public Utente() {
    }

    public Utente(String nome, String password, String email) {
        this.nome = nome;
        this.password = password;
        this.email = email;
    }

    // === METODI USER ===
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    // === GETTERS ===

    public int getUtenteId() {
        return utenteId;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public List<VideogiocoGiocato> getVideogiochiGiocati() {
        return videogiochiGiocati;
    }
    public List<LibroLetto> getLibriLetti() {
        return libriLetti;
    }
    public List<FilmVisto> getFilmVisti() {
        return filmVisti;
    }
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVideogiochiGiocati(List<VideogiocoGiocato> videogiochiGiocati) {
        this.videogiochiGiocati = videogiochiGiocati;
    }

    public void setLibriLetti(List<LibroLetto> libriLetti) {
        this.libriLetti = libriLetti;
    }

    public void setFilmVisti(List<FilmVisto> filmVisti) {
        this.filmVisti = filmVisti;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
