package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "videogiochi_giocati")
public class VideogiocoGiocato {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "videogioco_giocato_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videogiocoGiocatoId;

    // === MANY TO MANY ===

    @ManyToOne
    @JoinColumn( name = "videogioco_id")
    private Videogioco videogioco;

    @ManyToOne
    @JoinColumn( name = "utente_id")
    private Utente utente;

    // === ONE TO MANY ===

    @OneToOne
    @JoinColumn( name = "recensione_id")
    private Recensione recensione;

    // === CONSTRUCTOR ===

    public VideogiocoGiocato() {
    }

    public VideogiocoGiocato(int videogiocoGiocatoId, Videogioco videogioco, Utente utente, Recensione recensione) {
        this.videogiocoGiocatoId = videogiocoGiocatoId;
        this.videogioco = videogioco;
        this.utente = utente;
        this.recensione = recensione;
    }

    // === GETTERS ===

    public int getVideogiocoGiocatoId() {
        return videogiocoGiocatoId;
    }
    public Videogioco getVideogioco() {
        return videogioco;
    }
    public Utente getUtente() {
        return utente;
    }
    public Recensione getRecensione() {
        return recensione;
    }
}
