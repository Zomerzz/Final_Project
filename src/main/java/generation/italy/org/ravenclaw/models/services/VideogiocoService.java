package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Recensione;
import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;

import java.util.List;
import java.util.Optional;

public interface VideogiocoService {
    Optional<Videogioco> findVideogiocoById(int id);

    List<Videogioco> findAllVideogiochi();

    boolean deleteVideogiocoById(int id);

    //Fare da DTO a Videogioco prima di salvare
    Videogioco saveVideogioco(Videogioco videogioco, int idCasaPubblicazione, int idCasaProduzione);

    Videogioco updateVideogioco(Videogioco videogioco, int casaDiProduzioneId, int casaDiPubblicazioneId);

    List<Videogioco> searchVideogiochi(VideogiocoFilterCriteria filters);

    List<VideogiocoGiocato> findVideogiocoGiocatoByUtente(int utenteId);

    Optional<VideogiocoGiocato> findVideogiocoGiocatoById(int id);

    VideogiocoGiocato updateVideogiocoGiocato(VideogiocoGiocato videogiocoGiocato, Videogioco videogioco, int utenteId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;

    VideogiocoGiocato saveVideogiocoGiocato(VideogiocoGiocato vg, Videogioco videogioco, int utenteId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;

    boolean deleteVideogiocoGiocato(int id);
}
