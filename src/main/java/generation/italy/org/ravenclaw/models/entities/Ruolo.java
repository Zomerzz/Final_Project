package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ruoli")
public class Ruolo {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "ruolo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ruoloId;

    private String nome;

    // === ONE TO MANY === (questo blocco è tendenzialmente inutile, essendo che non ci interesserà mai avere una lista di tutti i Sound designer mai esistiti)

    //@OneToMany(mappedBy = "ruolo_id")
    //private List<CrewFilm> crewFilms = new ArrayList<>();

    //@OneToMany(mappedBy = "ruolo_id")
    //Private List<CrewVideogioco> crewVideogioco = new ArrayList<>();

    // === COSTRUTTORI ===

    public Ruolo() {
    }

    public Ruolo(String nome) {
        this.nome = nome;
    }

    // === GETTER ===

    public int getRuoloId() {
        return ruoloId;
    }

    public String getNome() {
        return nome;
    }
}
