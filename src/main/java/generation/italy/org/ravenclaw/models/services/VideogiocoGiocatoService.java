package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;

import java.util.List;
import java.util.Optional;

public interface VideogiocoGiocatoService {
    List<VideogiocoGiocato> findAll();

    List<VideogiocoGiocato> findByUtente(int utenteId);

    Optional<VideogiocoGiocato> findById(int id);
}
