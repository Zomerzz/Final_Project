package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Utente;
import generation.italy.org.ravenclaw.models.repositories.CasaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaCasaService implements CasaService{
    private CasaRepository casaRepository;

    public JpaCasaService(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    @Override
    public Optional<Casa> findById(int id){
        return casaRepository.findById(id);
    }

    @Override
    public List<Casa> findAll() {
        return casaRepository.findAll();
    }

    @Override
    public boolean deleteById(int id){
        casaRepository.deleteById(id);
        return true;
    }

    @Override
    public Casa save(Casa casa){
        Casa casaEsistente = casaRepository.findByNome(casa.getNome());
        return casaRepository.save(casa);
    }

    @Override
    public Casa updateCasa (Casa casa){
        Optional<Casa> optUtente = casaRepository.findById(casa.getCasaId());

        return casaRepository.save(casa);
    }

}
