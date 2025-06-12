package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.LibroLetto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroLettoRepository  extends JpaRepository<LibroLetto, Integer> {
    List<LibroLetto> findByUtenteUtenteId(int utenteId);

    Optional<LibroLetto> findByUtenteUtenteIdAndLibroLibroId(int utenteId, int libroId);
}
