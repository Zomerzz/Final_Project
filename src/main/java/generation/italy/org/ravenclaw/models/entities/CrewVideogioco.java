package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "crew_videogioco")
public class CrewVideogioco {

    // === ATRIBUTI ===

    @Id
    @Column(name = "crew_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int crewId;

    @ManyToOne
    @JoinColumn( name = "videogioco_id")
    private Videogioco videogioco;

    @ManyToOne
    @JoinColumn( name = "autore_id")
    private Autore autore;

    @ManyToOne
    @JoinColumn( name = "ruolo_id")
    private Ruolo ruolo;

    // === COSTRUTTORI ===

    public CrewVideogioco() {
    }

    public CrewVideogioco(Videogioco videogioco, Autore autore, Ruolo ruolo) {
        this.videogioco = videogioco;
        this.autore = autore;
        this.ruolo = ruolo;
    }

    // === GETTERS ===

    public int getCrewId() {
        return crewId;
    }

    public Videogioco getVideogioco() {
        return videogioco;
    }

    public Autore getAutore() {
        return autore;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }
}
