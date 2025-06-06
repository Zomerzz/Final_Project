package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Autore;

public class AutoreDto {
    private int id;
    private String nome;
    private String secondoNome;
    private String cognome;
    private String dataDiNascita;
    private String nationalita;

    public AutoreDto(int id, String nome, String secondoNome, String cognome, String dataDiNascita, String nazionalita){
        this.id = id;
        this.nome = nome;
        this.secondoNome = secondoNome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.nationalita = nazionalita;
    }
    public Autore toAutore(){
        return new Autore(nome,secondoNome,cognome,dataDiNascita,nationalita);
    }
    static public AutoreDto toDto(Autore a){
        return new AutoreDto(a.getAutoreId(), a.getNome(), a.getSecondoNome(),a.getCognome(), a.getDataDiNascita(), a.getNazionalita());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSecondoNome() {
        return secondoNome;
    }

    public void setSecondoNome(String secondoNome) {
        this.secondoNome = secondoNome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getNationalita() {
        return nationalita;
    }

    public void setNationalita(String nationalita) {
        this.nationalita = nationalita;
    }
}
