package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Casa;

public class CasaDto {
    private int casaId;
    private String nome;
    private String nazionalita;

    public CasaDto(){}

    public CasaDto(int casaId, String nome, String nazionalita) {
        this.casaId = casaId;
        this.nome = nome;
        this.nazionalita = nazionalita;
    }

    public static CasaDto toDto(Casa casa) {
        return new CasaDto(casa.getCasaId(), casa.getNome(), casa.getNazionalita());
    }

    public Casa toCasa(){
        return new Casa(nome, nazionalita);
    }

    //GETTER & SETTER

    public int getCasaId() {
        return casaId;
    }

    public void setCasaId(int casaId) {
        this.casaId = casaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }
}
