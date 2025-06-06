package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente {

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

    // === CONSTRUCTORS ===

    public Utente() {
    }

    public Utente(String nome, String password, String email) {
        this.nome = nome;
        this.password = password;
        this.email = email;
    }

    // === GETTERS ===

    public int getUtenteId() {
        return utenteId;
    }
    public String getNome() {
        return nome;
    }
    public String getPassword() {
        return password;
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
}
