package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Film;

import java.time.LocalDate;

public class FilmDto {

    private int id;
    private String titolo;
    private int durata;
    private LocalDate dataDiPubblicazione;
    private String descrizione;
    private CasaDto casaDiProduzione;
    private CasaDto casaDiPubblicazione;
    private int voto;
    private String imgUrl;

    public FilmDto(int id,
                   String titolo,
                   int durata,
                   LocalDate dataDiPubblicazione,
                   String descrizione,
                   CasaDto casaDiProduzione,
                   CasaDto casaDiPubblicazione,
                   int voto,
                   String imgUrl) {
        this.id = id;
        this.titolo = titolo;
        this.durata = durata;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.descrizione = descrizione;
        this.casaDiProduzione = casaDiProduzione;
        this.casaDiPubblicazione = casaDiPubblicazione;
        this.voto = voto;
        this.imgUrl = imgUrl;
    }

    public Film toFilm(){
        return new Film(titolo,durata,dataDiPubblicazione,descrizione,null,null,voto,imgUrl);
    }

    static public FilmDto toDto(Film f){
        return new FilmDto(f.getFilmId(),
                f.getTitolo(),
                f.getDurata(),
                f.getDataDiPubblicazione(),
                f.getDescrizione(),
                CasaDto.toDto(f.getCasaDiProduzione()),
                CasaDto.toDto(f.getCasaDiPubblicazione()),
                f.getVoto(),
                f.getImgUrl());
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getDurata() {
        return durata;
    }
    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataDiPubblicazione() {
        return dataDiPubblicazione;
    }

    public void setDataDiPubblicazione(LocalDate dataDiPubblicazione) {
        this.dataDiPubblicazione = dataDiPubblicazione;
    }

    public CasaDto getCasaDiProduzione() {
        return casaDiProduzione;
    }

    public void setCasaDiProduzione(CasaDto casaDiProduzione) {
        this.casaDiProduzione = casaDiProduzione;
    }

    public CasaDto getCasaDiPubblicazione() {
        return casaDiPubblicazione;
    }

    public void setCasaDiPubblicazione(CasaDto casaDiPubblicazione) {
        this.casaDiPubblicazione = casaDiPubblicazione;
    }

    public int getVoto() {
        return voto;
    }
    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

