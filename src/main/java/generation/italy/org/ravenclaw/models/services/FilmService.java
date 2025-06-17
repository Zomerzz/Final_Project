package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    Optional<Film> findFilmById(int id);
    List<Film> findAllFilms();
    Film saveFilm(Film film, int casaProduzioneId, int casaPubblicazioneId) throws EntityNotFoundException;
    boolean deleteFilm(int id);
    Page<Film> searchFilm(FilmFilterCriteria ffc);

    List<FilmVisto> findFilmVistoByUtente(int utenteId);

    Optional<FilmVisto> findFilmVistoById(int id);

    FilmVisto updateFilmVisto(FilmVisto filmVisto, int filmId, int utenteId, int recensioneId) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;

    FilmVisto saveFilmVisto(FilmVisto filmVisto, int filmId, int utenteId) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;


    boolean deleteFilmVisto(int id);

    Optional<FilmVisto> findFilmVistoByFilmIdAndUtenteId(int filmId, int utenteId);

    List<Film> findFilmConsigliatiByUtenteId(int utenteId);
}
