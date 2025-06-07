package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    Optional<Libro> findLibroById(int id);

    List<Libro> findAllLibri();

    Libro saveLibro(Libro libro, int casaEditriceId);

    Libro updateLibro(Libro libro, int casaEditriceId);

    boolean deleteLibro(int id);
}
