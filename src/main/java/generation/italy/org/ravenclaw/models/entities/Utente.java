package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @Column(name = "utente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int utenteId;
    private String nome;
    private String password;
    private String email;

    @OneToMany(mappedBy = "utente_id")
    private List<VideogiocoGiocato> videogiochiGiocati = new ArrayList<>();

    @OneToMany(mappedBy = "utente_id")
    private List<LibroLetto> libriLetti = new ArrayList<>();

    @OneToMany(mappedBy = "utente_id")
    private List<FilmVisto> filmVisti = new ArrayList<>();

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
