package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "videogiochi_giocati")
public class VideogiocoGiocato {
    @Id
    @Column(name = "videogioco_giocato_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videogiocoGiocatoId;

    @ManyToOne
    @JoinColumn( name = "videogioco_id")
    private Videogioco videogioco;

    @ManyToOne
    @JoinColumn( name = "utente_id")
    private Utente utente;

    @OneToOne
    @JoinColumn( name = "recensione_id")
    private Recensione recensione;

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
