package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recensioni")
public class Recensione {
    @Id
    @Column(name = "recensione_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recensioneId;

    private int voto;
    private String recensione;

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
