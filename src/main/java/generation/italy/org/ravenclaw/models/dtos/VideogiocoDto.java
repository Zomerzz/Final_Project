package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Videogioco;

import java.time.LocalDate;

public class VideogiocoDto {
    private int id;
    private String titolo;
    private int casaDiProduzioneId;
    private int casaDiPubblicazioneId;
    private LocalDate dataDiPubblicazione;
    private int oreStoriaPrincipale;
    private String descrizione;
    private int voto;
    private String imgUrl;

    public VideogiocoDto() {
    }

    public VideogiocoDto(int id, String titolo, int casaDiProduzioneId, int casaDiPubblicazioneId, LocalDate dataDiPubblicazione, int oreStoriaPrincipale, String descrizione, int voto, String imgUrl) {
        this.id = id;
        this.titolo = titolo;
        this.casaDiProduzioneId = casaDiProduzioneId;
        this.casaDiPubblicazioneId = casaDiPubblicazioneId;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.oreStoriaPrincipale = oreStoriaPrincipale;
        this.descrizione = descrizione;
        this.voto = voto;
        this.imgUrl = imgUrl;
    }

    static public VideogiocoDto toDto (Videogioco v){
        return new VideogiocoDto(v.getVideogiocoId(),v.getTitolo(),v.getCasaDiProduzione().getCasaId(),v.getCasaDiPubblicazione().getCasaId(),v.getDataDiPubblicazione(),v.getOreStoriaPrincipale(),v.getDescrizione(),v.getVoto(),v.getImgUrl());
    }
    public Videogioco toVideogioco (){
        return new Videogioco(titolo,null,null,dataDiPubblicazione,oreStoriaPrincipale,descrizione,voto,imgUrl);
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

    public int getCasaDiProduzioneId() {
        return casaDiProduzioneId;
    }

    public void setCasaDiProduzioneId(int casaDiProduzioneId) {
        this.casaDiProduzioneId = casaDiProduzioneId;
    }

    public int getCasaDiPubblicazioneId() {
        return casaDiPubblicazioneId;
    }

    public void setCasaDiPubblicazioneId(int casaDiPubblicazioneId) {
        this.casaDiPubblicazioneId = casaDiPubblicazioneId;
    }

    public LocalDate getDataDiPubblicazione() {
        return dataDiPubblicazione;
    }

    public void setDataDiPubblicazione(LocalDate dataDiPubblicazione) {
        this.dataDiPubblicazione = dataDiPubblicazione;
    }

    public int getOreStoriaPrincipale() {
        return oreStoriaPrincipale;
    }

    public void setOreStoriaPrincipale(int oreStoriaPrincipale) {
        this.oreStoriaPrincipale = oreStoriaPrincipale;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        descrizione = descrizione;
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
