package generation.italy.org.ravenclaw.models.searchCriteria;

import java.time.LocalDate;
import java.util.List;

public class VideogiocoFilterCriteria {
    private String titolo;
    private String nomeCasaDiProduzione;
    private String nomeCasaDiPubblicazione;
    private LocalDate minDataDiPubblicazione;
    private LocalDate maxDataDiPubblicazione;
    private Integer minOreDiGiocoStoriaPrincipale;
    private Integer maxOreDiGiocoStoriaPrincipale;
    private Integer minVoto;
    private Integer maxVoto;
    private List<Integer> tags;
    private int pageSize;
    private int numPage;
    private boolean orderByVoto;

    public VideogiocoFilterCriteria(String titolo,
                                    String nomeCasaDiProduzione,
                                    String nomeCasaDiPubblicazione,
                                    LocalDate minDataDiPubblicazione,
                                    LocalDate maxDataDiPubblicazione,
                                    Integer minOreDiGiocoStoriaPrincipale,
                                    Integer maxOreDiGiocoStoriaPrincipale,
                                    Integer minVoto, Integer maxVoto,
                                    List<Integer> tags,
                                    int pageSize,
                                    int numPage,
                                    boolean orderByVoto) {
        this.titolo = titolo;
        this.nomeCasaDiProduzione = nomeCasaDiProduzione;
        this.nomeCasaDiPubblicazione = nomeCasaDiPubblicazione;
        this.minDataDiPubblicazione = minDataDiPubblicazione;
        this.maxDataDiPubblicazione = maxDataDiPubblicazione;
        this.minOreDiGiocoStoriaPrincipale = minOreDiGiocoStoriaPrincipale;
        this.maxOreDiGiocoStoriaPrincipale = maxOreDiGiocoStoriaPrincipale;
        this.minVoto = minVoto;
        this.maxVoto = maxVoto;
        this.tags = tags;
        this.pageSize = pageSize;
        this.numPage = numPage;
        this.orderByVoto = orderByVoto;
    }
// === GETTER ===

    public String getTitolo() {
        return titolo;
    }

    public String getNomeCasaDiProduzione() {
        return nomeCasaDiProduzione;
    }

    public String getNomeCasaDiPubblicazione() {
        return nomeCasaDiPubblicazione;
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

    public int getPageSize() {
        return pageSize;
    }

    public int getNumPage() {
        return numPage;
    }

    public boolean isOrderByVoto() {
        return orderByVoto;
    }
}
