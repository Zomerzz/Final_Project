package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Recensione;
import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface VideogiocoService {
    Optional<Videogioco> findVideogiocoById(int id);

    List<Videogioco> findAllVideogiochi();

    boolean deleteVideogiocoById(int id);

    Videogioco saveVideogioco(Videogioco videogioco, int casaDiPubblicazioneId, int casaDiProduzioneId) throws EntityNotFoundException;

    Videogioco updateVideogioco(Videogioco videogioco, int casaDiPubblicazioneId, int casaDiProduzioneId) throws EntityNotFoundException;

    Page<Videogioco> searchVideogiochi(VideogiocoFilterCriteria filters);

    List<VideogiocoGiocato> findVideogiocoGiocatoByUtente(int utenteId);

    Optional<VideogiocoGiocato> findVideogiocoGiocatoById(int id);

    VideogiocoGiocato updateVideogiocoGiocato(VideogiocoGiocato videogiocoGiocato, int videogiocoId, int utenteId, int recensioneId) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;

    VideogiocoGiocato saveVideogiocoGiocato(VideogiocoGiocato vg, int videogiocoId, int utenteId) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;

    boolean deleteVideogiocoGiocato(int id);

    Optional<VideogiocoGiocato> findVideogiocoGiocatoByVideogiocoIdAndUtenteId(int videogiocoId, int utenteId);

    List<Videogioco> findVideogiochiConsigliatiByUtenteId(int utenteId);
}
