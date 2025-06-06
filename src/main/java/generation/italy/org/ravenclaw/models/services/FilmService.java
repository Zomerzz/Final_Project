package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    Optional<Film> findFilmById(int id);

    List<Film> findAllFilms();

    Film saveFilm(Film film, int casaDiProduzioneId, int casaDiPubblicazioneId);

    Film updateFilm(Film film, int casaDiProduzioneId, int casaDiPubblicazioneId);

    boolean deleteFilm(int id);
}
