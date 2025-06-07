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
}
