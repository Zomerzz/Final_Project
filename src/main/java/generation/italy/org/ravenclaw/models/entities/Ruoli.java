package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ruoli")
public class Ruoli {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "ruolo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ruoloId;

    private String nome;

    // === COSTRUTTORI ===

    public Ruoli() {
    }

    public Ruoli(String nome) {
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
