package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.request.PasswordUpdateRequest;
import generation.italy.org.ravenclaw.models.dtos.response.UtenteResponse;
import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.entities.Utente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UtenteService {
    @Transactional(readOnly = true)
    UtenteResponse getUserInfo();

    Optional<Utente> findUtenteById(int id) ;

    List<Utente> findAllUtenti() ; //mancava una d

    Utente saveUtente(Utente utente);

    Utente updateUtente(Utente utente);

    boolean deleteUtente(int id);

    @Transactional
    void updatePassword(PasswordUpdateRequest passwordUpdateRequest);
}
