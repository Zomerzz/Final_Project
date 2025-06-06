package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Casa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasaRepository  extends JpaRepository<Casa, Integer> {
    Casa findByNome(String nome);
}
