package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.*;

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
    private LocalDate dataDiPubblicazione;

    private String descrizione;

    // === MANY TO MANY ===

    @ManyToOne
    @JoinColumn(name = "casa_di_produzione_id")
    private Casa casaDiProduzione;

    @ManyToOne
    @JoinColumn(name = "casa_di_pubblicazione_id")
    private Casa casaDiPubblicazione;

    private int voto;

    @Column(name = "immagine_di_copertina")
    private String imgUrl;

    // === ONE TO MANY ===

    @OneToMany(mappedBy = "film")
    private List<CrewFilm> crew = new ArrayList<>();

    //=== MANY TO MANY ===

    @ManyToMany(mappedBy = "filmSet", fetch = FetchType.EAGER)
    private Set<Tag> tagSet = new HashSet<>();

    // === COSTRUTTORI ===

    public Film() {
    }

    public Film(String titolo, int durata, LocalDate dataDiPubblicazione, String descrizione, Casa casaDiProduzione, Casa casaDiPubblicazione, int voto, String imgUrl) {
        this.titolo = titolo;
        this.durata = durata;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.descrizione = descrizione;
        this.casaDiProduzione = casaDiProduzione;
        this.casaDiPubblicazione = casaDiPubblicazione;
        this.voto = voto;
        this.imgUrl = imgUrl;
    }

    @Override
    public int hashCode(){
        return Objects.hash(filmId);
    }

    @Override
    public boolean equals(Object film){
        if(this == film){
            return true;
        }
        if(film == null || getClass() != film.getClass()){
            return false;
        }
        Film l = (Film)film;
        return filmId == l.getFilmId();
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

    public LocalDate getDataDiPubblicazione() {
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

    public List<CrewFilm> getCrew() {
        return crew;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }
// === SETTER ===

    public void setCasaDiProduzione(Casa casaDiProduzione) {
        this.casaDiProduzione = casaDiProduzione;
    }

    public void setCasaDiPubblicazione(Casa casaDiPubblicazione) {
        this.casaDiPubblicazione = casaDiPubblicazione;
    }
}
