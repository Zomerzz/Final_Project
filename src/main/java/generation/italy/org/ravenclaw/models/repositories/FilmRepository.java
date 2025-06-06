package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository  extends JpaRepository<Film, Integer> {
}
