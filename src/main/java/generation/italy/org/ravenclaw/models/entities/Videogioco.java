package generation.italy.org.ravenclaw.models.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "videogiochi")
public class Videogioco {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "videogioco_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videogiocoId;

    private String titolo;

    @ManyToOne
    @JoinColumn(name = "casa_id")
    private Casa casaDiProduzione;

    @ManyToOne
    @JoinColumn(name = "casa_id")
    private Casa casaDiPubblicazione;

    @Column(name = "data_di_pubblicazione")
    private String dataDiPubblicazione;

    @Column(name = "ore_storia_principale")
    private Integer oreStoriaPrincipale;

    private String descrizione;

    private int voto;

    @Column(name = "immagine_di_copertina")
    private String imgUrl;

    // === ONE TO MANY ===

    @OneToMany(mappedBy = "videogioco_id")
    private List<CrewVideogioco> crew = new ArrayList<>();


    // === COSTRUTTORI ===

    public Videogioco() {
    }

    public Videogioco(String titolo, Casa casaDiProduzione, Casa casaDiPubblicazione, String dataDiPubblicazione, Integer oreStoriaPrincipale, String descrizione, int voto, String imgUrl) {
        this.titolo = titolo;
        this.casaDiProduzione = casaDiProduzione;
        this.casaDiPubblicazione = casaDiPubblicazione;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.oreStoriaPrincipale = oreStoriaPrincipale;
        this.descrizione = descrizione;
        this.voto = voto;
        this.imgUrl = imgUrl;
    }

    // === GETTER ===

    public int getVideogiocoId() {
        return videogiocoId;
    }

    public String getTitolo() {
        return titolo;
    }

    public Casa getCasaDiProduzione() {
        return casaDiProduzione;
    }

    public Casa getCasaDiPubblicazione() {
        return casaDiPubblicazione;
    }

    public String getDataDiPubblicazione() {
        return dataDiPubblicazione;
    }

    public Integer getOreStoriaPrincipale() {
        return oreStoriaPrincipale;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getVoto() {
        return voto;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
