package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecensioneRepository  extends JpaRepository<Recensione, Integer> {
    List<Recensione> findByFilmVistoFilmFilmId(int filmId);

    List<Recensione> findByLibroLettoLibroLibroId(int libroId);

    List<Recensione> findByVideogiocoGiocatoVideogiocoVideogiocoId(int videogiocoId);


}
