package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.FilmVisto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmVistoRepository  extends JpaRepository<FilmVisto, Integer> {
    List<FilmVisto> findByUtenteUtenteId(int utenteId);

    Optional<FilmVisto> findByUtenteUtenteIdAndFilmFilmId(int utenteId, int filmId);
}
