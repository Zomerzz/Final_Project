package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "casa")
public class Casa {

    //=== ATTRIBUTI ===

    @Id
    @Column(name = "casa_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int casaId;

    private String nome;

    private String nazionalita;

    // ===LISTE DI FILM===
    @OneToMany(mappedBy = "casaDiPubblicazione" )
    private List<Film> listaFilmPubblicati=new ArrayList<>();

    @OneToMany(mappedBy = "casaDiProduzione" )
    private List<Film> listaFilmProduzione=new ArrayList<>();

    // ===LISTE DI LIBRI===
    @OneToMany(mappedBy = "casaDiProduzione" )
    private List<Libro> listaLibriProduzione=new ArrayList<>();

    // ===LISTE DI GIOCHI===
    @OneToMany(mappedBy = "casaDiPubblicazione" )
    private List<Videogioco> listaGiochiPubblicati=new ArrayList<>();

    @OneToMany(mappedBy = "casaDiProduzione" )
    private List<Videogioco> listaGiochiProduzione=new ArrayList<>();

    // === COSTRUTTORI ===
    public Casa() {
    }

    public Casa(String nome, String nazionalita){

        // === VALORI EFFETTIVAMENTE UTIULI ===
        this.nome = nome;
        this.nazionalita = nazionalita;
    }

    // === GETTER E SETTER ===


    public int getCasaId() {
        return casaId;
    }

    public String getNome() {
        return nome;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public List<Film> getListaFilmPubblicati() {
        return listaFilmPubblicati;
    }

    public List<Film> getListaFilmProduzione() {
        return listaFilmProduzione;
    }

    public List<Libro> getListaLibriProdotti() {
        return listaLibriProduzione;
    }

    public List<Videogioco> getListaGiochiPubblicati() {
        return listaGiochiPubblicati;
    }

    public List<Videogioco> getListaGiochiProduzione() {
        return listaGiochiProduzione;
    }
}
