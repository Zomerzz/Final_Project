package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.FilmVisto;

import java.util.List;
import java.util.Optional;

public interface FilmVistoService {
    List<FilmVisto> findAll();

    List<FilmVisto> findByUtente(int utenteId);

    Optional<FilmVisto> findById(int id);

    FilmVisto update(FilmVisto filmVisto, int filmId, int utenteId, int recensioneId) throws EntityNotFoundException;

    FilmVisto save(FilmVisto filmVisto, int filmId, int utenteId, int recensioneId) throws EntityNotFoundException;

    boolean delete(int id);
}
