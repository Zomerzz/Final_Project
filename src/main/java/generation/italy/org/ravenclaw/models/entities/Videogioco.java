package generation.italy.org.ravenclaw.models.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

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
    @JoinColumn(name = "casa_di_produzione_id")
    private Casa casaDiProduzione;

    @ManyToOne
    @JoinColumn(name = "casa_di_pubblicazione_id")
    private Casa casaDiPubblicazione;

    @Column(name = "data_di_pubblicazione")
    private LocalDate dataDiPubblicazione;

    @Column(name = "ore_storia_principale")
    private int oreStoriaPrincipale;

    private String descrizione;

    private int voto;

    @Column(name = "immagine_di_copertina")
    private String imgUrl;

    // === ONE TO MANY ===

    @OneToMany(mappedBy = "videogioco")
    private List<CrewVideogioco> crew = new ArrayList<>();

    // === MANY TO MANY ===

    @ManyToMany(mappedBy = "videogiocoSet", fetch = FetchType.EAGER)
    private Set<Tag> tagSet = new HashSet<>();

    // === COSTRUTTORI ===

    public Videogioco() {
    }

    public Videogioco(String titolo, Casa casaDiProduzione, Casa casaDiPubblicazione, LocalDate dataDiPubblicazione, int oreStoriaPrincipale, String descrizione, int voto, String imgUrl) {
        this.titolo = titolo;
        this.casaDiProduzione = casaDiProduzione;
        this.casaDiPubblicazione = casaDiPubblicazione;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.oreStoriaPrincipale = oreStoriaPrincipale;
        this.descrizione = descrizione;
        this.voto = voto;
        this.imgUrl = imgUrl;
    }

    @Override
    public int hashCode(){
        return Objects.hash(videogiocoId);
    }

    @Override
    public boolean equals(Object videogioco){
        if(this == videogioco){
            return true;
        }
        if(videogioco == null || getClass() != videogioco.getClass()){
            return false;
        }
        Videogioco l = (Videogioco)videogioco;
        return videogiocoId == l.getVideogiocoId();
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

    public LocalDate getDataDiPubblicazione() {
        return dataDiPubblicazione;
    }

    public int getOreStoriaPrincipale() {
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

    public List<CrewVideogioco> getCrew() {
        return crew;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }
// === SETTER ===

    public void setVideogiocoId(int videogiocoId) {
        this.videogiocoId = videogiocoId;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setCasaDiProduzione(Casa casaDiProduzione) {
        this.casaDiProduzione = casaDiProduzione;
    }

    public void setCasaDiPubblicazione(Casa casaDiPubblicazione) {
        this.casaDiPubblicazione = casaDiPubblicazione;
    }

    public void setDataDiPubblicazione(LocalDate dataDiPubblicazione) {
        this.dataDiPubblicazione = dataDiPubblicazione;
    }

    public void setOreStoriaPrincipale(int oreStoriaPrincipale) {
        this.oreStoriaPrincipale = oreStoriaPrincipale;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCrew(List<CrewVideogioco> crew) {
        this.crew = crew;
    }


}

