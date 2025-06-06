package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "autori")
public class Autore {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "autore_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int autoreId;

    private String nome;

    @Column(name = "secondo_nome")
    private String secondoNome;

    private String cognome;

    @Column(name = "data_di_nascita")
    private String dataDiNascita;

    private String nazionalita;

    // === ONE TO MANY ===

    @OneToMany(mappedBy = "autore")
    private List<CrewFilm> crewFilms = new ArrayList<>();

    @OneToMany(mappedBy = "autore")
    private List<CrewVideogioco> crewVideogioco = new ArrayList<>();

    // === MANY TO MANY ===

    @ManyToMany
    @JoinTable(
            name = "scritto_da",
            joinColumns = @JoinColumn(name = "autore_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    private Set<Libro> libroSet = new HashSet<>();

    // === COSTRUTTORI ===

    public Autore() {
    }

    public Autore(String nome, String secondoNome, String cognome, String dataDiNascita, String nazionalita) {
        this.nome = nome;
        this.secondoNome = secondoNome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.nazionalita = nazionalita;
    }

    // === GETTER ===

    public int getAutoreId() {
        return autoreId;
    }

    public String getNome() {
        return nome;
    }

    public String getSecondoNome() {
        return secondoNome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public String getNazionalita() {
        return nazionalita;
    }
}
