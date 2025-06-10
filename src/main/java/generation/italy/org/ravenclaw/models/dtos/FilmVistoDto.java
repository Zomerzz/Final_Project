package generation.italy.org.ravenclaw.models.dtos;

import generation.italy.org.ravenclaw.models.entities.FilmVisto;

public class FilmVistoDto {
    private int filmVistoId;
    private int filmId;
    private int utenteId;
    private int recensioneId;

    public FilmVistoDto() {
    }

    public FilmVistoDto(int filmVistoId, int filmId, int utenteId, int recensioneId) {
        this.filmVistoId = filmVistoId;
        this.filmId = filmId;
        this.utenteId = utenteId;
        this.recensioneId = recensioneId;
    }

    private static FilmVistoDto toDto(FilmVisto filmVisto){
        return new FilmVistoDto(filmVisto.getFilmVistoId(),
                filmVisto.getFilm().getFilmId(),
                filmVisto.getUtente().getUtenteId(),
                filmVisto.getRecensione().getRecensioneId());
    }

    private FilmVisto toFilmVisto(){
        return new FilmVisto(filmVistoId, null, null, null);
    }

    public int getFilmVistoId() {
        return filmVistoId;
    }

    public void setFilmVistoId(int filmVistoId) {
        this.filmVistoId = filmVistoId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
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
