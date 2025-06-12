package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.dtos.request.RecensioneRequest;
import generation.italy.org.ravenclaw.models.entities.FilmVisto;
import generation.italy.org.ravenclaw.models.entities.Utente;

public class FilmVistoDto {
    private int filmVistoId;
    private FilmDto film;
    private int utenteId;
    private RecensioneDto recensione;

    public FilmVistoDto() {
    }

    public FilmVistoDto(int filmVistoId, FilmDto film, int utenteId) {
        this.filmVistoId = filmVistoId;
        this.film = film;
        this.utenteId = utenteId;
    }

    public FilmVistoDto(int filmVistoId, FilmDto film, int utenteId, RecensioneDto recensione) {
        this.filmVistoId = filmVistoId;
        this.film = film;
        this.utenteId = utenteId;
        this.recensione = recensione;
    }

    public static FilmVistoDto toDto(FilmVisto filmVisto){

        FilmVistoDto dto = new FilmVistoDto((filmVisto.getFilmVistoId()),
                FilmDto.toDto(filmVisto.getFilm()), filmVisto.getUtente().getUtenteId());
        if(filmVisto.getRecensione()!=null){
            dto.setRecensioneDto(RecensioneDto.toDto(filmVisto.getRecensione()));
        }
        return dto;
    }

    public FilmVisto toFilmVisto(){
        return new FilmVisto(filmVistoId, null, null, null);
    }

    public int getFilmVistoId() {
        return filmVistoId;
    }

    public void setFilmVistoId(int filmVistoId) {
        this.filmVistoId = filmVistoId;
    }

    public FilmDto getFilm() {
        return film;
    }

    public void setFilm(FilmDto film) {
        this.film = film;
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

    public void setRecensioneDto(RecensioneDto recensione) {
        this.recensione = recensione;
    }
}
