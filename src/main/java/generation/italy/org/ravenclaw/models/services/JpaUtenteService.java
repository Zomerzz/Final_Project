
package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.entities.Utente;
import generation.italy.org.ravenclaw.models.repositories.AutoreRepository;
import generation.italy.org.ravenclaw.models.repositories.UtenteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUtenteService implements UtenteService{
    private UtenteRepository utenteRepo;

    public JpaUtenteService(UtenteRepository utenteRepo) {
        this.utenteRepo = utenteRepo;
    }

    @Override
    public Optional<Utente> findUtenteById(int id)  {
        Optional<Utente> utenteOpt = utenteRepo.findById(id);
        return utenteOpt;
    }

    @Override
    public List<Utente> findAllUtenti() {
        List<Utente> utenti = utenteRepo.findAll();
        return utenti;
    }

    @Override
    public Utente saveUtente(Utente utente) {
        return utenteRepo.save(utente);
    }

    @Override
    public Utente updateUtente(Utente utente) {
        Optional<Utente> optUtente = utenteRepo.findById(utente.getUtenteId());

        return utenteRepo.save(utente);
    }

    @Override
    public boolean deleteUtente(int id) {
        utenteRepo.deleteById(id);
        return true;
    }

}

