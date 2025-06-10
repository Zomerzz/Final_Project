package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;
import generation.italy.org.ravenclaw.models.repositories.CasaRepository;
import generation.italy.org.ravenclaw.models.repositories.VideogiocoGiocatoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JpaVideogiocoGiocatoService implements VideogiocoGiocatoService {
    private VideogiocoGiocatoRepository videogiocoGiocatoRepo;

    @Autowired
    public JpaVideogiocoGiocatoService(VideogiocoGiocatoRepository videogiocoGiocatoRepo) {
        this.videogiocoGiocatoRepo = videogiocoGiocatoRepo;
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
}
