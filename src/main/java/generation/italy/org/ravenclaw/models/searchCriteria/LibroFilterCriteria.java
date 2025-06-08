package generation.italy.org.ravenclaw.models.searchCriteria;

import java.util.Date;
import java.util.List;

public class LibroFilterCriteria {
    private String titolo;
    private Integer numeroPagine;
    private Integer autoreId;
    private String autoreNome;
    private Integer casaEditriceId;
    private Date minData;
    private Date maxData;
    private Integer minVoto;
    private Integer maxVoto;
    private List<Integer> tags;

    public LibroFilterCriteria(String titolo, Integer numeroPagine, Integer autoreId, String autoreNome, Integer casaEditriceId, Date minData, Date maxData, Integer minVoto, Integer maxVoto, List<Integer> tags) {
        this.titolo = titolo;
        this.numeroPagine = numeroPagine;
        this.autoreId = autoreId;
        this.autoreNome = autoreNome;
        this.casaEditriceId = casaEditriceId;
        this.minData = minData;
        this.maxData = maxData;
        this.minVoto = minVoto;
        this.maxVoto = maxVoto;
        this.tags = tags;
    }

    // === GETTER ===

    public String getTitolo() {
        return titolo;
    }

    public Integer getNumeroPagine() {
        return numeroPagine;
    }

    public Integer getAutoreId(){
        return autoreId;
    }

    public String getAutoreNome() { return autoreNome; }

    public Integer getCasaEditriceId() {
        return casaEditriceId;
    }

    public Date getMinData() {
        return minData;
    }

    public Date getMaxData() {
        return maxData;
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