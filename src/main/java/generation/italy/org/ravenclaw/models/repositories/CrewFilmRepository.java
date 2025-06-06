package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.CrewFilm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewFilmRepository  extends JpaRepository<CrewFilm, Integer> {
}
