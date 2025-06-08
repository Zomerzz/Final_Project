package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.repositories.AutoreRepository;
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
        List<Autore> autori = autoreRepo.findAll();
        return autori;
    }

    @Override
    public Autore saveAutore(Autore autore) {
        return autoreRepo.save(autore);
    }

    @Override
    public Autore updateAutore(Autore autore)  {
        return saveAutore(autore);
    }

    @Override
    public boolean deleteAutore(int id){
        autoreRepo.deleteById(id);
        return true;
    }
}
