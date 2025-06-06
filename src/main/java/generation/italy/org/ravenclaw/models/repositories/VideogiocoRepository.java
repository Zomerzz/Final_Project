package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Videogioco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogiocoRepository extends JpaRepository<Videogioco, Integer> {
}
