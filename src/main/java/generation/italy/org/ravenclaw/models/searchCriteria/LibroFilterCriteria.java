package generation.italy.org.ravenclaw.models.searchCriteria;

import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class LibroFilterCriteria {
    private String titolo;
    private Integer numeroPagine;
    private String autoreNome;
    private String casaEditriceNome;
    private LocalDate minData;
    private LocalDate maxData;
    private Integer minVoto;
    private Integer maxVoto;
    private List<Integer> tags;
    private int pageSize;
    private int numPage;
    private String sort;

    public LibroFilterCriteria(String titolo, Integer numeroPagine, String autoreNome, String casaEditriceNome, LocalDate minData, LocalDate maxData, Integer minVoto, Integer maxVoto, List<Integer> tags, int pageSize, int numPage, String sort) {
        this.titolo = titolo;
        this.numeroPagine = numeroPagine;
        this.autoreNome = autoreNome;
        this.casaEditriceNome = casaEditriceNome;
        this.minData = minData;
        this.maxData = maxData;
        this.minVoto = minVoto;
        this.maxVoto = maxVoto;
        this.tags = tags;
        this.pageSize = pageSize;
        this.numPage = numPage;
        this.sort = sort;
    }

    // === GETTER ===

    public String getTitolo() {
        return titolo;
    }

    public Integer getNumeroPagine() {
        return numeroPagine;
    }

    public String getAutoreNome() { return autoreNome; }

    public String getCasaEditriceNome() {
        return casaEditriceNome;
    }

    public LocalDate getMinData() {
        return minData;
    }

    public LocalDate getMaxData() {
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

    public int getPageSize() {
        return pageSize;
    }

    public int getNumPage() {
        return numPage;
    }

    public String getSort() {
        return sort;
    }
}