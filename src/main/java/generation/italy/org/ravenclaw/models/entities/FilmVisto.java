package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "film_visti")
public class FilmVisto {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "film_visto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmVistoId;

    // === MANY TO MANY ===

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn( name = "film_id")
    private Film film;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn( name = "utente_id")
    private Utente utente;

    // === ONE TO MANY ===

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn( name = "recensione_id")
    private Recensione recensione;

    // === COSTRUTTORI ===

    public FilmVisto() {
    }

    public FilmVisto(int filmVistoId, Film film, Utente utente, Recensione recensione) {
        this.filmVistoId = filmVistoId;
        this.film = film;
        this.utente = utente;
        this.recensione = recensione;
    }

    // === GETTER ===

    public int getFilmVistoId() {
        return filmVistoId;
    }
    public Film getFilm() {
        return film;
    }
    public Utente getUtente() {
        return utente;
    }
    public Recensione getRecensione() {
        return recensione;
    }

    // === SETTER ===

    public void setFilmVistoId(int filmVistoId) {
        this.filmVistoId = filmVistoId;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setRecensione(Recensione recensione) {
        this.recensione = recensione;
    }
}
