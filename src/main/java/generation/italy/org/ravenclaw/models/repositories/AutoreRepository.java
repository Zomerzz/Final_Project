package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoreRepository  extends JpaRepository<Autore, Integer> {
}
