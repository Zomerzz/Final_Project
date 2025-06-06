package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "libri_letti")
public class LibroLetto {

    // === ATTRIBUTI

    @Id
    @Column(name = "libro_letto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int libroLettoId;

    // === MANY TO MANY ===

    @ManyToOne
    @JoinColumn( name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn( name = "utente_id")
    private Utente utente;

    // === ONE TO MANY ===

    @OneToOne
    @JoinColumn( name = "recensione_id")
    private Recensione recensione;

    // === COSTRUTTORI ===

    public LibroLetto() {
    }

    public LibroLetto(Libro libro, Utente utente, Recensione recensione) {
        this.libro = libro;
        this.utente = utente;
        this.recensione = recensione;
    }


    // === GETTERS ===

    public int getLibroLettoId() {
        return libroLettoId;
    }
    public Libro getLibro() {
        return libro;
    }
    public Utente getUtente() {
        return utente;
    }
    public Recensione getRecensione() {
        return recensione;
    }
}
