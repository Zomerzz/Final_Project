package generation.italy.org.ravenclaw.models.searchCriteria;

import java.time.LocalDate;
import java.util.List;

public class VideogiocoFilterCriteria {
    private String titolo;
    private String nomeCasaProduzione;
    private String nomeCasaPubblicazione;
    private LocalDate minDataDiPubblicazione;
    private LocalDate maxDataDiPubblicazione;
    private Integer minOreDiGiocoStoriaPrincipale;
    private Integer maxOreDiGiocoStoriaPrincipale;
    private Integer minVoto;
    private Integer maxVoto;
    private List<Integer> tags;

    public VideogiocoFilterCriteria(String titolo, String nomeCasaProduzione, String nomeCasaPubblicazione, LocalDate minDataDiPubblicazione, LocalDate maxDataDiPubblicazione, Integer minOreDiGiocoStoriaPrincipale,Integer maxOreDiGiocoStoriaPrincipale,  Integer minVoto, Integer maxVoto, List<Integer> tags) {
        this.titolo = titolo;
        this.nomeCasaProduzione = nomeCasaProduzione;
        this.nomeCasaPubblicazione = nomeCasaPubblicazione;
        this.minDataDiPubblicazione = minDataDiPubblicazione;
        this.maxDataDiPubblicazione = maxDataDiPubblicazione;
        this.minOreDiGiocoStoriaPrincipale = minOreDiGiocoStoriaPrincipale;
        this.maxOreDiGiocoStoriaPrincipale = maxOreDiGiocoStoriaPrincipale;
        this.minVoto = minVoto;
        this.maxVoto = maxVoto;
        this.tags = tags;

    }


    // === GETTER ===

    public String getTitolo() {
        return titolo;
    }

    public String getNomeCasaProduzione() {
        return nomeCasaProduzione;
    }

    public String getNomeCasaPubblicazione() {
        return nomeCasaPubblicazione;
    }

    public LocalDate getMinDataDiPubblicazione() {
        return minDataDiPubblicazione;
    }

    public LocalDate getMaxDataDiPubblicazione() {
        return maxDataDiPubblicazione;
    }

    public Integer getMinOreDiGiocoStoriaPrincipale() {
        return minOreDiGiocoStoriaPrincipale;
    }

    public Integer getMaxOreDiGiocoStoriaPrincipale() {
        return maxOreDiGiocoStoriaPrincipale;
    }

    public Integer getMinVoto() {
        return minVoto;
    }

    public Integer getMaxVoto() {
        return maxVoto;
    }

    public List<Integer> getTags() {
        return tags;
    }
}
