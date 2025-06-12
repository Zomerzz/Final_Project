package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.DataException;
import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.LibroDto;
import generation.italy.org.ravenclaw.models.dtos.RecensioneDto;
import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.entities.LibroLetto;
import generation.italy.org.ravenclaw.models.entities.Recensione;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    Optional<Libro> findLibroById(int id) throws DataException;

    List<Libro> findAllLibri() throws DataException;

    Libro saveLibro(Libro libro, Casa casaEditrice) throws DataException, EntityNotFoundException;

    Libro updateLibro(Libro libro, int casaEditriceId) throws DataException, EntityNotFoundException;

    boolean deleteLibro(int id) throws DataException, EntityNotFoundException;

    List<Libro> searchLibro(LibroFilterCriteria filters);

    List<LibroLetto> findLibroLettoByUtente(int utenteId);

    Optional<LibroLetto> findLibroLettoById(int id);

    LibroLetto updateLibroLetto(LibroLetto libroLetto, int libroId, int utenteId, int recensioneId) throws EntityNotFoundException;

    LibroLetto saveLibroLetto(LibroLetto libroLetto, int libroId, int utenteId, int recensioneId) throws EntityNotFoundException;

    boolean deleteLibroLetto(int id);
}
