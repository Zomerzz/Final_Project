package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "libri_letti")
public class LibroLetto {
    @Id
    @Column(name = "libro_letto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int libroLettoId;

    @ManyToOne
    @JoinColumn( name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn( name = "utente_id")
    private Utente utente;

    @OneToOne
    @JoinColumn( name = "recensione_id")
    private Recensione recensione;

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
