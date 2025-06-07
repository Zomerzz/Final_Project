package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.DataException;
import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    Optional<Libro> findLibroById(int id) throws DataException;

    List<Libro> findAllLibri() throws DataException;

    Libro saveLibro(Libro libro, int casaEditriceId) throws DataException, EntityNotFoundException;

    Libro updateLibro(Libro libro, int casaEditriceId) throws DataException, EntityNotFoundException;

    boolean deleteLibro(int id) throws DataException, EntityNotFoundException;
}
