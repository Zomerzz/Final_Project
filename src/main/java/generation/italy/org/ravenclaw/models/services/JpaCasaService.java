package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.repositories.CasaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaCasaService implements CasaService{
    private CasaRepository casaRepository;

    public JpaCasaService(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    @Override
    public Casa findById(int id){
        return casaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Casa> findAll() {
        return casaRepository.findAll();
    }

    @Override
    public void deleteById(int id){
        if(!casaRepository.existsById(id)){
            throw new EntityNotFoundException("Casa produttrice non trovata");
        }
        casaRepository.deleteById(id);
    }

    @Override
    public Casa save(Casa casa){
        Casa casaEsistente = casaRepository.findByNome(casa.getNome());
        return casaRepository.save(casa);
    }

}
