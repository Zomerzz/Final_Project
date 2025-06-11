package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;

public class VideogiocoGiocatoDto {
    private int videogiocoGiocatoId;
    private int videogiocoId;
    private int utenteId;
    private int recensioneId;

    public VideogiocoGiocatoDto(){}

    public VideogiocoGiocatoDto(int videogiocoGiocatoId, int videogiocoId, int utenteId, int recensioneId) {
        this.videogiocoGiocatoId = videogiocoGiocatoId;
        this.videogiocoId = videogiocoId;
        this.utenteId = utenteId;
        this.recensioneId = recensioneId;
    }

    public static VideogiocoGiocatoDto toDto(VideogiocoGiocato videogiocoGiocato){
        return new VideogiocoGiocatoDto(videogiocoGiocato.getVideogiocoGiocatoId(),
                videogiocoGiocato.getVideogioco().getVideogiocoId(),
                videogiocoGiocato.getUtente().getUtenteId(),
                videogiocoGiocato.getRecensione().getRecensioneId());
    }

    public VideogiocoGiocato toVideogiocoGiocato(){
        return new VideogiocoGiocato(videogiocoGiocatoId, null, null, null);
    }

    public int getVideogiocoGiocatoId() {
        return videogiocoGiocatoId;
    }

    public void setVideogiocoGiocatoId(int videogiocoGiocatoId) {
        this.videogiocoGiocatoId = videogiocoGiocatoId;
    }

    public int getVideogiocoId() {
        return videogiocoId;
    }

    public void setVideogiocoId(int videogiocoId) {
        this.videogiocoId = videogiocoId;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public int getRecensioneId() {
        return recensioneId;
    }

    public void setRecensioneId(int recensioneId) {
        this.recensioneId = recensioneId;
    }
}
