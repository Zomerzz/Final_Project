package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.repositories.CasaRepository;
import generation.italy.org.ravenclaw.models.repositories.VideogiocoRepository;
import generation.italy.org.ravenclaw.models.repositories.criteriaRepositories.CriteriaVideogiocoRepository;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaVideogiocoService implements VideogiocoService{
    private VideogiocoRepository videogiocoRepository;
    private CasaRepository casaRepository;
    private CriteriaVideogiocoRepository criteriaVideogiocoRepository;

    @Autowired
    public JpaVideogiocoService(VideogiocoRepository videogiocoRepository, CasaRepository casaRepository, CriteriaVideogiocoRepository criteriaVideogiocoRepository) {
        this.videogiocoRepository = videogiocoRepository;
        this.casaRepository = casaRepository;
        this.criteriaVideogiocoRepository = criteriaVideogiocoRepository;
    }

    @Override
    public Optional<Videogioco> findById(int id) {
        return videogiocoRepository.findById(id);
    }

    @Override
    public List<Videogioco> findAll() {
        return videogiocoRepository.findAll();
    }

    @Override
    public boolean deleteById(int id){
        if(!videogiocoRepository.existsById(id)){
            throw new EntityNotFoundException("Videogioco non trovato");
        }
        videogiocoRepository.deleteById(id);
        return true;
    }


    //Fare da DTO a Videogioco prima di salvare
    @Override
    public Videogioco save(Videogioco videogioco, int idCasaPubblicazione, int idCasaProduzione) {
        Optional<Casa> optCasaPubVideogioco = casaRepository.findById(idCasaPubblicazione);
        Optional<Casa> optCasaProdVideogioco = casaRepository.findById(idCasaProduzione);

        Casa casaDiProduzione= optCasaProdVideogioco.orElseThrow(EntityNotFoundException::new);
        Casa casaDiPubblicazione = optCasaPubVideogioco.orElseThrow(EntityNotFoundException::new);

        videogioco.setCasaDiPubblicazione(casaDiPubblicazione);
        videogioco.setCasaDiProduzione(casaDiProduzione);

        return videogiocoRepository.save(videogioco);
    }

    @Override
    public Videogioco updateVideogioco(Videogioco videogioco, int casaDiProduzioneId, int casaDiPubblicazioneId) {
        videogiocoRepository.findById(videogioco.getVideogiocoId()).orElseThrow(EntityNotFoundException::new);
        return save(videogioco, casaDiProduzioneId, casaDiPubblicazioneId);
    }

    @Override
    public List<Videogioco> searchProducts(VideogiocoFilterCriteria filters) {
        return criteriaVideogiocoRepository.searchVideogiocoByFilter(filters);
    }

}
