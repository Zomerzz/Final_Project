package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository  extends JpaRepository<Libro, Integer> {
}
