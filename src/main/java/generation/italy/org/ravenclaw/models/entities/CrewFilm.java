package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "crew_film")
public class CrewFilm {

    // === ATRIBUTI ===

    @Id
    @Column(name = "crew_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int crewId;

    @ManyToOne
    @JoinColumn( name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn( name = "autore_id")
    private Autore autore;

    @ManyToOne
    @JoinColumn( name = "ruolo_id")
    private Ruolo ruolo;

    // === COSTRUTTORI ===

    public CrewFilm() {
    }

    public CrewFilm(Film film,Autore autore, Ruolo ruolo) {
        this.film = film;
        this.ruolo = ruolo;
        this.autore = autore;
    }

    // === GETTERS ===

    public int getCrewId() {
        return crewId;
    }

    public Film getFilm() {
        return film;
    }

    public Autore getAutore() {
        return autore;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }
}
