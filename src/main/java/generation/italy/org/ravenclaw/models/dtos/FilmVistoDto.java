package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.FilmVisto;
import generation.italy.org.ravenclaw.models.entities.Utente;

public class FilmVistoDto {
    private int filmVistoId;
    private FilmDto film;
    private int utenteId;
    private RecensioneDto recensioneDto;

    public FilmVistoDto() {
    }

    public FilmVistoDto(int filmVistoId, FilmDto film, int utenteId, RecensioneDto recensioneDto) {
        this.filmVistoId = filmVistoId;
        this.film = film;
        this.utenteId = utenteId;
        this.recensioneDto = recensioneDto;
    }

    public static FilmVistoDto toDto(FilmVisto filmVisto){
        return new FilmVistoDto((filmVisto.getFilmVistoId()),
                FilmDto.toDto(filmVisto.getFilm()), filmVisto.getUtente().getUtenteId(), RecensioneDto.toDto(filmVisto.getRecensione()));

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

    public RecensioneDto getRecensioneDto() {
        return recensioneDto;
    }

    public void setRecensioneDto(RecensioneDto recensioneDto) {
        this.recensioneDto = recensioneDto;
    }
}
