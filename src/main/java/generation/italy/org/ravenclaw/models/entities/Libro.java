package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "libri")
public class Libro {

    // === ATTRIBUTI ===

    @Id
    @Column(name = "libro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int libroId;

    private String titolo;

    @Column(name = "numero_di_pagine")
    private int numeroPagine;

    @ManyToOne
    @JoinColumn(name = "casa_editrice_id")
    private Casa casaEditrice;

    private String descrizione;

    @Column(name = "anno_di_pubblicazione")
    private LocalDate dataDiPubblicazione;

    private int voto;

    @Column(name = "immagine_di_copertina")
    private String imgUrl;

    // === MANY TO MANY ===

    @ManyToMany(mappedBy = "libroSet", fetch = FetchType.EAGER)
    private Set<Autore> autoreSet = new HashSet<>();

    @ManyToMany(mappedBy = "libroSet", fetch = FetchType.EAGER)
    private Set<Tag> tagSet = new HashSet<>();

    // === CONSTRUTTORI ===

    public Libro() {
    }

    public Libro(String titolo, int numeroPagine, Casa casaEditrice, String descrizione, LocalDate dataDiPubblicazione, int voto, String imgUrl) {
        this.titolo = titolo;
        this.numeroPagine = numeroPagine;
        this.casaEditrice = casaEditrice;
        this.descrizione = descrizione;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.voto = voto;
        this.imgUrl = imgUrl;
    }

    @Override
    public int hashCode(){
        return Objects.hash(libroId);
    }

    @Override
    public boolean equals(Object libro){
        if(this == libro){
            return true;
        }
        if(libro == null || getClass() != libro.getClass()){
            return false;
        }
        Libro l = (Libro)libro;
        return libroId == l.getLibroId();
    }

// === GETTER ===

    public int getLibroId() {
        return libroId;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public Casa casaDiProduzione() {
        return casaEditrice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public LocalDate getDataDiPubblicazione() {
        return dataDiPubblicazione;
    }

    public int getVoto() {
        return voto;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Casa getCasaEditrice() {
        return casaEditrice;
    }

    public Set<Autore> getAutoreSet() {
        return autoreSet;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    // === SETTER ===

    public void setCasaEditrice(Casa casaEditrice) {
        this.casaEditrice = casaEditrice;
    }
}
