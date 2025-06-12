package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Libro;

import java.time.LocalDate;

public class LibroDto {
    private int libroId;
    private String titolo;
    private int numeroPagine;
    private String descrizione;
    private LocalDate dataDiPubblicazione;
    private CasaDto casaEditrice;
    private int voto;
    private String imgUrl;

    public LibroDto() {
    }

    public LibroDto(int libroId, String titolo, int numeroPagine, String descrizione,LocalDate dataDiPubblicazione, CasaDto casaEditrice, int voto, String imgUrl) {
        this.libroId = libroId;
        this.titolo = titolo;
        this.numeroPagine = numeroPagine;
        this.descrizione = descrizione;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.voto = voto;
        this.casaEditrice = casaEditrice;
        this.imgUrl = imgUrl;
    }

    public static LibroDto toDto(Libro libro){
        return new LibroDto(libro.getLibroId(),
                libro.getTitolo(),
                libro.getNumeroPagine(),
                libro.getDescrizione(),
                libro.getDataDiPubblicazione(),
                CasaDto.toDto(libro.casaDiProduzione()),
                libro.getVoto(),
                libro.getImgUrl());
    }

    public Libro toLibro(){
        return new Libro(titolo, numeroPagine, null, descrizione, dataDiPubblicazione, voto, imgUrl);
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public CasaDto getCasaEditrice() {
        return casaEditrice;
    }

    public void setCasaEditrice(CasaDto casaEditrice) {
        this.casaEditrice = casaEditrice;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public LocalDate getDataDiPubblicazione() {
        return dataDiPubblicazione;
    }

    public void setDataDiPubblicazione(LocalDate dataDiPubblicazione) {
        this.dataDiPubblicazione = dataDiPubblicazione;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}