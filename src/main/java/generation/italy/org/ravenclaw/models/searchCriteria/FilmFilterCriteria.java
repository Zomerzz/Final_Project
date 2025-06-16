package generation.italy.org.ravenclaw.models.searchCriteria;

import java.time.LocalDate;
import java.util.List;

public class FilmFilterCriteria {
    private String titolo;
    private String casaDiProduzioneNome;
    private String casaDiPubblicazioneNome;
    private LocalDate dataDiPubblicazione;
    private LocalDate minData;
    private LocalDate maxData;
    private List<Integer> tags;
    private Integer minVoto;
    private Integer maxVoto;
    //crew
    private String autoreNome;
    private int pageSize;
    private int numPage;
    private String sort;

    public FilmFilterCriteria(String titolo,
                              String casaDiProduzioneNome,
                              String casaDiPubblicazioneNome,
                              LocalDate dataDiPubblicazione,
                              LocalDate minData,
                              LocalDate maxData,
                              List<Integer> tags,
                              Integer minVoto,
                              Integer maxVoto,
                              String autoreNome,
                              int pageSize,
                              int numPage,
                              String sort) {
        this.titolo = titolo;
        this.casaDiProduzioneNome = casaDiProduzioneNome;
        this.casaDiPubblicazioneNome = casaDiPubblicazioneNome;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.minData = minData;
        this.maxData = maxData;
        this.tags = tags;
        this.minVoto = minVoto;
        this.maxVoto = maxVoto;
        this.autoreNome = autoreNome;
        this.pageSize = pageSize;
        this.numPage = numPage;
        this.sort = sort;
    }

    //=== GETTER E SETTER ===
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCasaDiProduzioneNome() {
        return casaDiProduzioneNome;
    }

    public void setCasaDiProduzioneNome(String casaDiProduzioneNome) {
        this.casaDiProduzioneNome = casaDiProduzioneNome;
    }

    public String getCasaDiPubblicazioneNome() {
        return casaDiPubblicazioneNome;
    }

    public void setCasaDiPubblicazioneNome(String casaDiPubblicazioneNome) {
        this.casaDiPubblicazioneNome = casaDiPubblicazioneNome;
    }

    public LocalDate getDataDiPubblicazione() {
        return dataDiPubblicazione;
    }

    public void setDataDiPubblicazione(LocalDate dataDiPubblicazione) {
        this.dataDiPubblicazione = dataDiPubblicazione;
    }

    public LocalDate getMinData() {
        return minData;
    }

    public void setMinData(LocalDate minData) {
        this.minData = minData;
    }

    public LocalDate getMaxData() {
        return maxData;
    }

    public void setMaxData(LocalDate maxData) {
        this.maxData = maxData;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public Integer getMinVoto() {
        return minVoto;
    }

    public void setMinVoto(Integer minVoto) {
        this.minVoto = minVoto;
    }

    public Integer getMaxVoto() {
        return maxVoto;
    }

    public void setMaxVoto(Integer maxVoto) {
        this.maxVoto = maxVoto;
    }

    public String getAutoreNome() {
        return autoreNome;
    }

    public void setAutoreNome(String autoreNome) {
        this.autoreNome = autoreNome;
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
