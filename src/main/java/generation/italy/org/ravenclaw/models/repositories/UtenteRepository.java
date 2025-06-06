package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository  extends JpaRepository<Utente, Integer> {
}
