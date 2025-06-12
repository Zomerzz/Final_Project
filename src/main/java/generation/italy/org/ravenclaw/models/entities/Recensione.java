package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recensioni")
public class Recensione {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "recensione_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recensioneId;

    private int voto;
    private String recensione;


    @OneToOne(mappedBy = "recensione")
    private FilmVisto filmVisto;

    @OneToOne(mappedBy = "recensione")
    private LibroLetto libroLetto;

    @OneToOne(mappedBy = "recensione")
    private VideogiocoGiocato videogiocoGiocato;


    // === CONSTRUTTORI ===

    public Recensione() {
    }

    public Recensione(int voto, String recensione) {
        this.voto = voto;
        this.recensione = recensione;
    }

    // === GETTERS ===

    public String getRecensione() {
        return recensione;
    }
    public int getVoto() {
        return voto;
    }
    public int getRecensioneId() {
        return recensioneId;
    }
}
