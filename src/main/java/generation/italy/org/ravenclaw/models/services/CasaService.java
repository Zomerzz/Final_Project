package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Videogioco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CasaService {
    Optional<Casa> findById(int id);

    List<Casa> findAll();

    void deleteById(int id);

    Casa save(Casa casa);

    Casa updateCasa(Casa casa);
}
