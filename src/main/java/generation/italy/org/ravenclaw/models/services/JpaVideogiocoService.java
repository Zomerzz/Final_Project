package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.repositories.*;
import generation.italy.org.ravenclaw.models.repositories.criteriaRepositories.CriteriaVideogiocoRepository;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private RecensioneRepository recensioneRepository;


    @Autowired
    public JpaVideogiocoService(VideogiocoRepository videogiocoRepository,
                                CasaRepository casaRepository,
                                CriteriaVideogiocoRepository criteriaVideogiocoRepository,
                                VideogiocoGiocatoRepository videogiocoGiocatoRepository,
                                UtenteRepository utenteRepository,
                                RecensioneRepository recensioneRepository) {
        this.videogiocoRepository = videogiocoRepository;
        this.casaRepository = casaRepository;
        this.criteriaVideogiocoRepository = criteriaVideogiocoRepository;
        this.videogiocoGiocatoRepository = videogiocoGiocatoRepository;
        this.utenteRepository = utenteRepository;
        this.recensioneRepository = recensioneRepository;
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
        videogiocoRepository.deleteById(id);
        return true;
    }

    @Override
    public Videogioco saveVideogioco(Videogioco videogioco, int casaDiPubblicazioneId, int casaDiProduzioneId) throws EntityNotFoundException {
        Optional<Casa> optProduzione = casaRepository.findById(casaDiProduzioneId);
        Optional<Casa> optPubblicazione = casaRepository.findById(casaDiPubblicazioneId);

        Casa casaDiProduzione = optProduzione.orElseThrow(()-> new EntityNotFoundException(Casa.class));
        Casa casaDiPubblicazione = optPubblicazione.orElseThrow(()-> new EntityNotFoundException(Casa.class));

        videogioco.setCasaDiPubblicazione(casaDiPubblicazione);
        videogioco.setCasaDiProduzione(casaDiProduzione);
        return videogiocoRepository.save(videogioco);
    }

    @Override
    public Videogioco updateVideogioco(Videogioco videogioco, int casaDiPubblicazioneId, int casaDiProduzioneId) throws EntityNotFoundException {
        return saveVideogioco(videogioco, casaDiPubblicazioneId, casaDiProduzioneId);
    }

    @Override
    public Page<Videogioco> searchVideogiochi(VideogiocoFilterCriteria filters) {
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
    public VideogiocoGiocato updateVideogiocoGiocato(VideogiocoGiocato videogiocoGiocato,
                                                     int videogiocoId,
                                                     int utenteId,
                                                     int recensioneId) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        videogiocoGiocato.setRecensione(recensioneRepository.findById(recensioneId).orElseThrow(()-> new EntityNotFoundException(Recensione.class)));
        return saveVideogiocoGiocato(videogiocoGiocato, videogiocoId, utenteId);
    }

    @Override
    public VideogiocoGiocato saveVideogiocoGiocato(VideogiocoGiocato vg, int videogiocoId, int utenteId) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        Optional<Utente> optionalUtente = utenteRepository.findById(utenteId);
        Optional<Videogioco> optionalVideogioco = videogiocoRepository.findById(videogiocoId);

        Utente u = optionalUtente.orElseThrow(() -> new generation.italy.org.ravenclaw.exceptions.EntityNotFoundException(Utente.class));
        Videogioco videogioco = optionalVideogioco.orElseThrow(()-> new EntityNotFoundException(Videogioco.class));

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
