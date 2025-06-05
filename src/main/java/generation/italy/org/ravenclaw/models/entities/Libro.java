package generation.italy.org.ravenclaw.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
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
    @JoinColumn(name = "casa_id")
    private Casa casaDiProduzione;

    private String descrizione;

    @Column(name = "data_di_pubblicazione")
    private String dataDiPubblicazione;

    private int voto;

    @Column(name = "immagine_di_copertina")
    private String imgUrl;

    // === MANY TO MANY ===

    @ManyToMany(mappedBy = "libroSet")
    private Set<Autore> autoreSet = new HashSet<>();



    // === CONSTRUTTORI ===

    public Libro() {
    }

    public Libro(String titolo, int numeroPagine, Casa casaDiProduzione, String descrizione, String dataDiPubblicazione, int voto, String imgUrl) {
        this.titolo = titolo;
        this.numeroPagine = numeroPagine;
        this.casaDiProduzione = casaDiProduzione;
        this.descrizione = descrizione;
        this.dataDiPubblicazione = dataDiPubblicazione;
        this.voto = voto;
        this.imgUrl = imgUrl;
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
        return casaDiProduzione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getDataDiPubblicazione() {
        return dataDiPubblicazione;
    }

    public int getVoto() {
        return voto;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
