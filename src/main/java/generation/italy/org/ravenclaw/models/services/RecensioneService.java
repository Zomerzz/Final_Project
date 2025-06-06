package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Recensione;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RecensioneService {

    Optional<Recensione> findRecensioneById(int id);

    List<Recensione> findAllRecensioni();

    Recensione saveRecensione(Recensione recensione);

    Recensione updateRecensione(Recensione recensione);

    boolean deleteRecensione(int id);
}
