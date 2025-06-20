package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.entities.Libro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class LibroDto {
    private int id;
    private String titolo;
    private int numeroPagine;
    private String descrizione;
    private LocalDate dataDiPubblicazione;
    private CasaDto casaEditrice;
    private int voto;
    private String imgUrl;
    private Set<String> autori;

    public LibroDto() {
    }

    public LibroDto(int id, String titolo, int numeroPagine, String descrizione, LocalDate dataDiPubblicazione, CasaDto casaEditrice, int voto, String imgUrl, Set<Autore> autori) {
        this.id = id;
        this.titolo = titolo;
        this.numeroPagine = numeroPagine;
        this.descrizione = descrizione;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.voto = voto;
        this.casaEditrice = casaEditrice;
        this.imgUrl = imgUrl;
        this.autori = autori.stream().map(autore->{
            return autore.getNome()+" "+autore.getCognome()+" ";
        }).collect(Collectors.toSet());
    }

    public static LibroDto toDto(Libro libro){
        return new LibroDto(libro.getLibroId(),
                libro.getTitolo(),
                libro.getNumeroPagine(),
                libro.getDescrizione(),
                libro.getDataDiPubblicazione(),
                CasaDto.toDto(libro.casaDiProduzione()),
                libro.getVoto(),
                libro.getImgUrl(),
                libro.getAutoreSet());
    }

    public Libro toLibro(){
        return new Libro(titolo, numeroPagine, null, descrizione, dataDiPubblicazione, voto, imgUrl);
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

    public Set<String> getAutori() {
        return autori;
    }

    public void setAutori(Set<String> autori) {
        this.autori = autori;
    }
}