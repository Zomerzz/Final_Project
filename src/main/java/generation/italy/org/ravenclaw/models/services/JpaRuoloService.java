package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Ruolo;
import generation.italy.org.ravenclaw.models.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaRuoloService implements RuoloService{
    private final RuoloRepository ruoloRepo;
    @Autowired
    public JpaRuoloService(RuoloRepository ruoloRepo) {
        this.ruoloRepo = ruoloRepo;
    }

    @Override
    public Optional<Ruolo> findById(int id) {
        return ruoloRepo.findById(id);
    }

    @Override
    public List<Ruolo> findAll() {
        return ruoloRepo.findAll();
    }

    @Override
    public boolean deleteRuolo(int id) {
        Optional<Ruolo> exists = findById(id);
        if(exists.isPresent()){
            ruoloRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Ruolo saveRuolo(Ruolo ruolo) {
        return ruoloRepo.save(ruolo);
    }
}
