package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    private String nome;

    @Column(name = "genere")
    private boolean isGenere;

    // === COSTRUTTORI ===


    public Tag() {
    }

    public Tag(String nome, boolean isGenere) {
        this.nome = nome;
        this.isGenere = isGenere;
    }

    // === GETTER ===

    public int getTagId() {
        return tagId;
    }

    public String getNome() {
        return nome;
    }

    public boolean isGenere() {
        return isGenere;
    }
}
