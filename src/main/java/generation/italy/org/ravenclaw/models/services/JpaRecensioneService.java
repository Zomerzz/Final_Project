package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Recensione;
import generation.italy.org.ravenclaw.models.repositories.RecensioneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaRecensioneService implements RecensioneService{
    private RecensioneRepository recensioneRepo;

    @Autowired
    public JpaRecensioneService(RecensioneRepository recensioneRepo){
        this.recensioneRepo = recensioneRepo;
    }

    @Override
    public Optional<Recensione> findRecensioneById(int id){
        return recensioneRepo.findById(id);
    }

    @Override
    public List<Recensione> findAllRecensioni(){
        return recensioneRepo.findAll();
    }

    @Override
    public Recensione saveRecensione(Recensione recensione){
        return recensioneRepo.save(recensione);
    }

    @Override
    public Recensione updateRecensione(Recensione recensione){
        Optional<Recensione> optRecensione = recensioneRepo.findById(recensione.getRecensioneId());
        if(optRecensione.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return recensioneRepo.save(recensione);
    }

    @Override
    public boolean deleteRecensione(int id){
        Optional<Recensione> optRecensione = recensioneRepo.findById(id);
        if(optRecensione.isEmpty()) {
            throw new EntityNotFoundException();
        }
        recensioneRepo.deleteById(id);
        return true;
    }
}
