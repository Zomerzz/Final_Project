package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.Recensione;

public class RecensioneDto {
    private int recensioneId;
    private int voto;
    private String recensione;

    public RecensioneDto(int recensioneId, int voto, String recensione) {
        this.recensioneId = recensioneId;
        this.voto = voto;
        this.recensione = recensione;
    }

    public Recensione toRecensione(){
        return new Recensione(voto, recensione);
    }

    public static RecensioneDto toDto(Recensione r){
        return new RecensioneDto(r.getRecensioneId(), r.getVoto(), r.getRecensione());
    }


    //GETTER AND SETTER
    public int getRecensioneId() {
        return recensioneId;
    }

    public void setRecensioneId(int recensioneId) {
        this.recensioneId = recensioneId;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getRecensione() {
        return recensione;
    }

    public void setRecensione(String recensione) {
        this.recensione = recensione;
    }
}
