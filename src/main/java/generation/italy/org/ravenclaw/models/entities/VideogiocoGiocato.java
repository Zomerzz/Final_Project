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

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn( name = "videogioco_id")
    private Videogioco videogioco;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn( name = "utente_id")
    private Utente utente;

    // === ONE TO MANY ===

    @OneToOne(cascade = CascadeType.REMOVE)
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

    // === SETTER ===

    public void setVideogiocoGiocatoId(int videogiocoGiocatoId) {
        this.videogiocoGiocatoId = videogiocoGiocatoId;
    }

    public void setVideogioco(Videogioco videogioco) {
        this.videogioco = videogioco;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setRecensione(Recensione recensione) {
        this.recensione = recensione;
    }
}
