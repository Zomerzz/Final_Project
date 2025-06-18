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

    @ManyToOne()
    @JoinColumn( name = "libro_id")
    private Libro libro;

    @ManyToOne()
    @JoinColumn( name = "utente_id")
    private Utente utente;

    // === ONE TO MANY ===

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn( name = "recensione_id")
    private Recensione recensione;

    // === COSTRUTTORI ===

    public LibroLetto() {
    }

    public LibroLetto(int libroLettoId, Libro libro, Utente utente, Recensione recensione) {
        this.libroLettoId = libroLettoId;
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

    // === SETTER ===

    public void setLibroLettoId(int libroLettoId) {
        this.libroLettoId = libroLettoId;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setRecensione(Recensione recensione) {
        this.recensione = recensione;
    }
}
