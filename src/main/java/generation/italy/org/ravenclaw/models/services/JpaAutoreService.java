package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.repositories.AutoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaAutoreService implements AutoreService{

    private AutoreRepository autoreRepo;

    @Autowired
    public JpaAutoreService(AutoreRepository autoreRepo) {
        this.autoreRepo = autoreRepo;
    }

    @Override
    public Optional<Autore> findAutoreById(int id) {
        return autoreRepo.findById(id);
    }

    @Override
    public List<Autore> findAllAutori() { // mancava una d
        return autoreRepo.findAll();
    }

    @Override
    public Autore saveAutore(Autore autore) {
        return autoreRepo.save(autore);
    }

    @Override
    public Autore updateAutore(Autore autore) {
        Optional<Autore> optAutore = autoreRepo.findById(autore.getAutoreId());
        if(optAutore.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return saveAutore(autore);
    }

    @Override
    public boolean deleteAutore(int id) {
        Optional<Autore> optAutore = autoreRepo.findById(id);
        if(optAutore.isEmpty()) {
            throw new EntityNotFoundException();
        }
        autoreRepo.deleteById(id);
        return true;
    }
}
