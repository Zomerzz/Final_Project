package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Film;
import generation.italy.org.ravenclaw.models.entities.FilmVisto;
import generation.italy.org.ravenclaw.models.entities.Recensione;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    Optional<Film> findFilmById(int id);
    List<Film> findAllFilms();
    Film saveFilm(Film film, int casaProdId, int casaPubbId);
    boolean deleteFilm(int id);
    List<Film> searchFilm(FilmFilterCriteria ffc);

    List<FilmVisto> findFilmVistoByUtente(int utenteId);

    Optional<FilmVisto> findFilmVistoById(int id);

    FilmVisto updateFilmVisto(FilmVisto filmVisto, Film film, int utenteId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;

    FilmVisto saveFilmVisto(FilmVisto filmVisto, Film film, int utenteId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;

    boolean deleteFilmVisto(int id);
}
