package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "film_visti")
public class FilmVisto {
    @Id
    @Column(name = "film_visto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmVistoId;

    @ManyToOne
    @JoinColumn( name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn( name = "utente_id")
    private Utente utente;

    @OneToOne
    @JoinColumn( name = "recensione_id")
    private Recensione recensione;

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
