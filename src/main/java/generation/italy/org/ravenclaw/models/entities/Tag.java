package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @Column(name = "tag_name")
    private String nome;

    @Column(name = "genere")
    private boolean isGenere;

    // ===MANY TO MANY ===

    @ManyToMany
    @JoinTable(
            name = "libri_assegnamento_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    private Set<Libro> libroSet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "film_assegnamento_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private Set<Film> filmSet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "videogiochi_assegnamento_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "videogioco_id")
    )
    private Set<Videogioco> videogiocoSet = new HashSet<>();

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
