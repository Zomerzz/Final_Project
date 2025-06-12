package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideogiocoGiocatoRepository  extends JpaRepository<VideogiocoGiocato, Integer> {
    List<VideogiocoGiocato> findByUtenteUtenteId(int utenteId);

    Optional<VideogiocoGiocato> findByUtenteUtenteIdAndVideogiocoVideogiocoId(int utenteId, int videogiocoId);
}
