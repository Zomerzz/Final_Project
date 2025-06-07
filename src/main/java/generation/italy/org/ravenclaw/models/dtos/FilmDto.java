package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Film;

public class FilmDto {

    private int id;
    private String titolo;
    private int durata;
    private String dataDiPubblicazione;
    private String descrizione;
    private int casaDiProduzioneId;
    private int casaDiPubblicazioneId;
    private int voto;
    private String imgUrl;

    public FilmDto(int id, String titolo, int durata, String descrizione, int voto, String imgUrl) {
        this.id = id;
        this.titolo = titolo;
        this.durata = durata;
        this.descrizione = descrizione;
        this.voto = voto;
        this.imgUrl = imgUrl;
    }

    public Film toFilm(){
        return new Film(titolo,durata,dataDiPubblicazione,descrizione,null,null,voto,imgUrl);
    }

    static public FilmDto toDto(Film f){
        return new FilmDto(f.getFilmId(),f.getTitolo(),f.getDurata(),f.getDescrizione(),f.getVoto(),f.getImgUrl());
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

    public int getCasaDiProduzione() {
        return casaDiProduzioneId;
    }
    public void setCasaDiProduzione(int casaDiProduzioneId) {
        this.casaDiProduzioneId = casaDiProduzioneId;
    }

    public int getCasaDiPubblicazione() {
        return casaDiPubblicazioneId;
    }
    public void setCasaDiPubblicazione(int casaDiPubblicazione) {
        this.casaDiPubblicazioneId = casaDiPubblicazione;
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

