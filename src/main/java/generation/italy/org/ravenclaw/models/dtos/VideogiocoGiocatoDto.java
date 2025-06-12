package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;

public class VideogiocoGiocatoDto {
    private int videogiocoGiocatoId;
    private VideogiocoDto videogioco;
    private int utenteId;
    private RecensioneDto recensione;

    public VideogiocoGiocatoDto(){}

    public VideogiocoGiocatoDto(int videogiocoGiocatoId, VideogiocoDto videogioco, int utenteId, RecensioneDto recensione) {
        this.videogiocoGiocatoId = videogiocoGiocatoId;
        this.videogioco = videogioco;
        this.utenteId = utenteId;
        this.recensione = recensione;
    }

    public static VideogiocoGiocatoDto toDto(VideogiocoGiocato videogiocoGiocato){
        return new VideogiocoGiocatoDto(videogiocoGiocato.getVideogiocoGiocatoId(),
                VideogiocoDto.toDto(videogiocoGiocato.getVideogioco()),
                videogiocoGiocato.getUtente().getUtenteId(),
                RecensioneDto.toDto(videogiocoGiocato.getRecensione()));
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

    public VideogiocoDto getVideogioco() {
        return videogioco;
    }

    public void setVideogioco(VideogiocoDto videogioco) {
        this.videogioco = videogioco;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public RecensioneDto getRecensione() {
        return recensione;
    }

    public void setRecensione(RecensioneDto recensione) {
        this.recensione = recensione;
    }
}
