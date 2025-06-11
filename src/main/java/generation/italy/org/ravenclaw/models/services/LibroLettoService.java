package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.LibroLetto;

import java.util.List;
import java.util.Optional;

public interface LibroLettoService {
    List<LibroLetto> findAll();

    List<LibroLetto> findByUtente(int utenteId);

    Optional<LibroLetto> findById(int id);

    LibroLetto update(LibroLetto libroLetto, int libroId, int utenteId, int recensioneId) throws EntityNotFoundException;

    LibroLetto save(LibroLetto libroLetto, int libroId, int utenteId, int recensioneId) throws EntityNotFoundException;

    boolean delete(int id);
}
