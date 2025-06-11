package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaVideogiocoGiocatoService implements VideogiocoGiocatoService {
    private VideogiocoGiocatoRepository videogiocoGiocatoRepo;
    private VideogiocoRepository videogiocoRepo;
    private UtenteRepository utenteRepo;
    private RecensioneRepository recensioneRepo;

    @Autowired
    public JpaVideogiocoGiocatoService(VideogiocoGiocatoRepository videogiocoGiocatoRepo,
                                       VideogiocoRepository videogiocoRepo,
                                       UtenteRepository utenteRepo,
                                       RecensioneRepository recensioneRepo) {
        this.videogiocoGiocatoRepo = videogiocoGiocatoRepo;
        this.videogiocoRepo = videogiocoRepo;
        this.utenteRepo = utenteRepo;
        this.recensioneRepo = recensioneRepo;
    }

    @Override
    public List<VideogiocoGiocato> findAll() {
        return videogiocoGiocatoRepo.findAll();
    }

    @Override
    public List<VideogiocoGiocato> findByUtente(int utenteId) {
        return videogiocoGiocatoRepo.findByUtenteUtenteId(utenteId);
    }

    @Override
    public Optional<VideogiocoGiocato> findById(int id) {
        return videogiocoGiocatoRepo.findById(id);
    }

    @Override
    public VideogiocoGiocato update(VideogiocoGiocato videogiocoGiocato, int videogiocoId, int utenteId, int recensioneId) throws EntityNotFoundException {
        return save(videogiocoGiocato, videogiocoId, utenteId, recensioneId);
    }

    @Override
    public VideogiocoGiocato save(VideogiocoGiocato vg, int videogiocoId, int utenteId, int recensioneId) throws EntityNotFoundException {
        Optional<Videogioco> optionalVideogioco = videogiocoRepo.findById(videogiocoId);
        Optional<Utente> optionalUtente = utenteRepo.findById(utenteId);
        Optional<Recensione> optionalRecensione = recensioneRepo.findById(recensioneId);

        Videogioco v = optionalVideogioco.orElseThrow(() -> new EntityNotFoundException(Videogioco.class));
        Utente u = optionalUtente.orElseThrow(() -> new EntityNotFoundException(Utente.class));

        if(optionalRecensione.isPresent()){
            Recensione r = optionalRecensione.get();
            vg.setRecensione(r);
        }

        vg.setVideogioco(v);
        vg.setUtente(u);

        return videogiocoGiocatoRepo.save(vg);
    }

    @Override
    public boolean delete(int id) {
        videogiocoGiocatoRepo.deleteById(id);
        return true;
    }
}
