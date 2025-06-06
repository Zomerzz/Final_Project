package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Videogioco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VideogiocoService {
    Videogioco findById(int id);
    List<Videogioco> findAll();
    void deleteById(int id);
    Videogioco save(Videogioco videogioco, int casaDiPubblicazione, int casaDiProduzione);
    Videogioco updateVideogioco(Videogioco videogioco, int casaDiProduzioneId, int casaDiPubblicazioneId);
}
