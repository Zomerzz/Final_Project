package generation.italy.org.ravenclaw.models.dtos.request;

import generation.italy.org.ravenclaw.models.entities.Recensione;

public class RecensioneRequest {
    private int recensioneId;
    private int voto;
    private String recensione;
    private String type;
    private int operaId;
    private int utenteId;

    public RecensioneRequest() {
    }

    public RecensioneRequest(int recensioneId, int voto, String recensione, String type, int operaId, int utenteId) {
        this.recensioneId = recensioneId;
        this.voto = voto;
        this.recensione = recensione;
        this.type = type;
        this.operaId = operaId;
        this.utenteId = utenteId;
    }

    public Recensione toRecensione(){
        return new Recensione(recensioneId, voto, recensione);
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOperaId() {
        return operaId;
    }

    public void setOperaId(int operaId) {
        this.operaId = operaId;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }
}