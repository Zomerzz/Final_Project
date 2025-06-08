package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Videogioco;

import java.util.List;
import java.util.Optional;

public interface VideogiocoService {
    Optional<Videogioco> findById(int id);
    List<Videogioco> findAll();
    boolean deleteById(int id);
    Videogioco save(Videogioco videogioco, int casaDiPubblicazione, int casaDiProduzione);
    Videogioco updateVideogioco(Videogioco videogioco, int casaDiProduzioneId, int casaDiPubblicazioneId);
}
