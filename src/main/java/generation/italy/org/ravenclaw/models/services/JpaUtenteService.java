package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.entities.Utente;
import generation.italy.org.ravenclaw.models.repositories.AutoreRepository;
import generation.italy.org.ravenclaw.models.repositories.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUtenteService implements UtenteService{
    private UtenteRepository utenteRepo;

    @Override
    public Optional<Utente> findUtenteById(int id) {
        return utenteRepo.findById(id);
    }

    @Override
    public List<Utente> findAllUtenti() {
        return utenteRepo.findAll();
    }

    @Override
    public Utente saveUtente(Utente utente) {
        return utenteRepo.save(utente);
    }

    @Override
    public Utente updateUtente(Utente utente) {
        Optional<Utente> optUtente = utenteRepo.findById(utente.getUtenteId());
        if(optUtente.isEmpty()){
            throw new EntityNotFoundException();
        }
        return utenteRepo.save(utente);
    }

    @Override
    public boolean deleteUtente(int id) {
        Optional<Utente> optUtente = utenteRepo.findById(id);
        if(optUtente.isEmpty()){
            throw new EntityNotFoundException();
        }
        utenteRepo.deleteById(id);
        return true;
    }

}
