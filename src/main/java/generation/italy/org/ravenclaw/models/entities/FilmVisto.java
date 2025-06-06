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

    @ManyToOne
    @JoinColumn( name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn( name = "utente_id")
    private Utente utente;

    // === ONE TO MANY ===

    @OneToOne
    @JoinColumn( name = "recensione_id")
    private Recensione recensione;

    // === COSTRUTTORI ===

    public FilmVisto() {
    }

    public FilmVisto(Film film, Utente utente, Recensione recensione) {
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
}
