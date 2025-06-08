package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Ruolo;
import java.util.List;
import java.util.Optional;


public interface RuoloService {
    Optional<Ruolo> findById (int id);
    List<Ruolo> findAll();

    boolean deleteRuolo (int id);

    Ruolo saveRuolo(Ruolo ruolo);

}
