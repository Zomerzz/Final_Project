package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "film")
public class Film {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmId;

    private String titolo;

    private int durata;
    @Column(name = "data_di_pubblicazione")
    private String dataDiPubblicazione;

    private String descrizione;

    // === MANY TO MANY ===

    @ManyToOne
    @JoinColumn(name = "casa_di_produzione")
    private Casa casaDiProduzione;

    @ManyToOne
    @JoinColumn(name = "casa_di_pubblicazione")
    private Casa casaDiPubblicazione;

    private int voto;

    @Column(name = "immagine_di_copertina")
    private String imgUrl;

    // === ONE TO MANY ===

    @OneToMany(mappedBy = "film")
    private List<CrewFilm> crew = new ArrayList<>();

    // === COSTRUTTORI ===

    public Film() {
    }

    public Film(String titolo, int durata, String dataDiPubblicazione, String descrizione, Casa casaDiProduzione, Casa casaDiPubblicazione, int voto, String imgUrl) {
        this.titolo = titolo;
        this.durata = durata;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.descrizione = descrizione;
        this.casaDiProduzione = casaDiProduzione;
        this.casaDiPubblicazione = casaDiPubblicazione;
        this.voto = voto;
        this.imgUrl = imgUrl;
    }

    // === GETTER ===

    public int getFilmId() {
        return filmId;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getDurata() {
        return durata;
    }

    public String getDataDiPubblicazione() {
        return dataDiPubblicazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Casa getCasaDiProduzione() {
        return casaDiProduzione;
    }

    public Casa getCasaDiPubblicazione() {
        return casaDiPubblicazione;
    }

    public int getVoto() {
        return voto;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    // === SETTER ===

    public void setCasaDiProduzione(Casa casaDiProduzione) {
        this.casaDiProduzione = casaDiProduzione;
    }

    public void setCasaDiPubblicazione(Casa casaDiPubblicazione) {
        this.casaDiPubblicazione = casaDiPubblicazione;
    }
}
