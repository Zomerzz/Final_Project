package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.entities.Utente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UtenteService {
    Optional<Utente> findUtenteById(int id) ;

    List<Utente> findAllUtenti() ; //mancava una d

    Utente saveUtente(Utente utente);

    Utente updateUtente(Utente utente);

    boolean deleteUtente(int id);
}
