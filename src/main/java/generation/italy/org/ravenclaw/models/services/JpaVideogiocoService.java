package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.repositories.*;
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
    private VideogiocoGiocatoRepository videogiocoGiocatoRepository;
    private UtenteRepository utenteRepository;


    @Autowired
    public JpaVideogiocoService(VideogiocoRepository videogiocoRepository,
                                CasaRepository casaRepository,
                                CriteriaVideogiocoRepository criteriaVideogiocoRepository,
                                VideogiocoGiocatoRepository videogiocoGiocatoRepository,
                                UtenteRepository utenteRepository) {
        this.videogiocoRepository = videogiocoRepository;
        this.casaRepository = casaRepository;
        this.criteriaVideogiocoRepository = criteriaVideogiocoRepository;
        this.videogiocoGiocatoRepository = videogiocoGiocatoRepository;
        this.utenteRepository = utenteRepository;
    }

    @Override
    public Optional<Videogioco> findVideogiocoById(int id) {
        return videogiocoRepository.findById(id);
    }

    @Override
    public List<Videogioco> findAllVideogiochi() {
        return videogiocoRepository.findAll();
    }

    @Override
    public boolean deleteVideogiocoById(int id){
        if(!videogiocoRepository.existsById(id)){
            throw new EntityNotFoundException("Videogioco non trovato");
        }
        videogiocoRepository.deleteById(id);
        return true;
    }

    @Override
    public Videogioco saveVideogioco(Videogioco videogioco, Casa casaDiPubblicazione, Casa casaDiProduzione) {
        videogioco.setCasaDiPubblicazione(casaDiPubblicazione);
        videogioco.setCasaDiProduzione(casaDiProduzione);
        return videogiocoRepository.save(videogioco);
    }

    @Override
    public Videogioco updateVideogioco(Videogioco videogioco, Casa casaDiProduzione, Casa casaDiPubblicazione) {
        videogiocoRepository.findById(videogioco.getVideogiocoId()).orElseThrow(EntityNotFoundException::new);
        return saveVideogioco(videogioco, casaDiProduzione, casaDiPubblicazione);
    }

    @Override
    public List<Videogioco> searchVideogiochi(VideogiocoFilterCriteria filters) {
        return criteriaVideogiocoRepository.searchVideogiocoByFilter(filters);
    }

    @Override
    public List<VideogiocoGiocato> findVideogiocoGiocatoByUtente(int utenteId) {
        return videogiocoGiocatoRepository.findByUtenteUtenteId(utenteId);
    }

    @Override
    public Optional<VideogiocoGiocato> findVideogiocoGiocatoById(int id) {
        return videogiocoGiocatoRepository.findById(id);
    }

    @Override
    public VideogiocoGiocato updateVideogiocoGiocato(VideogiocoGiocato videogiocoGiocato, Videogioco videogioco, int utenteId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        return saveVideogiocoGiocato(videogiocoGiocato, videogioco, utenteId, recensione);
    }

    @Override
    public VideogiocoGiocato saveVideogiocoGiocato(VideogiocoGiocato vg, Videogioco videogioco, int utenteId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        Optional<Utente> optionalUtente = utenteRepository.findById(utenteId);
        Utente u = optionalUtente.orElseThrow(() -> new generation.italy.org.ravenclaw.exceptions.EntityNotFoundException(Utente.class));

        if(recensione != null){
            vg.setRecensione(recensione);
        }

        vg.setVideogioco(videogioco);
        vg.setUtente(u);

        return videogiocoGiocatoRepository.save(vg);
    }

    @Override
    public boolean deleteVideogiocoGiocato(int id) {
        videogiocoGiocatoRepository.deleteById(id);
        return true;
    }

}
