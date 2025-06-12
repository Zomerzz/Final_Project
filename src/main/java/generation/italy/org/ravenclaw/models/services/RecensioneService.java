package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
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

    List<Recensione> findRecensioneByFilmId(int filmId);

    List<Recensione> findRecensioneByLibroId(int libroId);

    List<Recensione> findRecensioneByVideogiocoId(int videogiocoId);

    void addRecensioneToFilmVisto(int utenteId, int filmId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;

    void addRecensioneToLibroLetto(int utenteId, int operaId, Recensione recensione) throws EntityNotFoundException;

    void addRecensioneToVideogiocoGiocato(int utenteId, int videogiocoId, Recensione recensione) throws EntityNotFoundException;

    void addRecensioneToRegistrazione(String type, int utenteId, int operaId, Recensione recensione) throws EntityNotFoundException;

    //List<Recensione> findRecensioneByUtenteId(int utenteId);
}
