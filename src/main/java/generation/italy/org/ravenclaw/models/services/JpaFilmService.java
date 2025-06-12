package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.repositories.*;
import generation.italy.org.ravenclaw.models.repositories.criteriaRepositories.CriteriaFilmRepository;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaFilmService implements FilmService {
    private FilmRepository filmRepo;
    private CasaRepository casaRepo;
    private CriteriaFilmRepository criteriaFRepo;
    private FilmVistoRepository filmVistoRepo;
    private UtenteRepository utenteRepo;
    private RecensioneRepository recensioneRepo;

    @Autowired
    public JpaFilmService(FilmRepository filmRepo,
                          CasaRepository casaRepo,
                          CriteriaFilmRepository criteriaFRepo,
                          FilmVistoRepository filmVistoRepo,
                          UtenteRepository utenteRepo,
                          RecensioneRepository recensioneRepo){
        this.filmRepo = filmRepo;
        this.casaRepo = casaRepo;
        this.criteriaFRepo = criteriaFRepo;
        this.filmVistoRepo = filmVistoRepo;
        this.utenteRepo = utenteRepo;
        this.recensioneRepo = recensioneRepo;
    }

    @Override
    public Optional<Film> findFilmById(int id) {
        return filmRepo.findById(id);
    }

    @Override
    public List<Film> findAllFilms() {
        return filmRepo.findAll();
    }

    @Override
    public Film saveFilm(Film film, int casaProduzioneId, int casaPubblicazioneid) throws EntityNotFoundException {
        Optional<Casa> optProduzione = casaRepo.findById(casaProduzioneId);
        Optional<Casa> optPubblicazione = casaRepo.findById(casaPubblicazioneid);

        Casa casaDiProduzione = optProduzione.orElseThrow(()-> new EntityNotFoundException(Casa.class));
        Casa casaDiPubblicazione = optPubblicazione.orElseThrow(()-> new EntityNotFoundException(Casa.class));


        film.setCasaDiProduzione(casaDiProduzione);
        film.setCasaDiPubblicazione(casaDiPubblicazione);
        return filmRepo.save(film);
    }

    @Override
    public boolean deleteFilm(int id){
        filmRepo.deleteById(id);
        return true;
    }

    @Override
    public Page<Film> searchFilm(FilmFilterCriteria ffc) {
        return criteriaFRepo.searchFilmByFilters(ffc);
    }


    @Override
    public List<FilmVisto> findFilmVistoByUtente(int utenteId) {
        return filmVistoRepo.findByUtenteUtenteId(utenteId);
    }

    @Override
    public Optional<FilmVisto> findFilmVistoById(int id) {
        return filmVistoRepo.findById(id);
    }

    @Override
    public FilmVisto updateFilmVisto(FilmVisto filmVisto, int filmId, int utenteId, int recensioneId) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        return saveFilmVisto(filmVisto, filmId, utenteId, recensioneId);
    }

    @Override
    public FilmVisto saveFilmVisto(FilmVisto filmVisto, int filmId, int utenteId, int recensioneId) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        Optional<Utente> optionalUtente = utenteRepo.findById(utenteId);
        Optional<Film> optionalFilm = filmRepo.findById(filmId);

        Utente u = optionalUtente.orElseThrow(() -> new EntityNotFoundException(Utente.class));
        Film f = optionalFilm.orElseThrow(() -> new EntityNotFoundException(Utente.class));

        if(recensioneId != 0){
            filmVisto.setRecensione(recensioneRepo.findById(recensioneId).orElseThrow(()-> new EntityNotFoundException(Recensione.class)));
        }

        filmVisto.setFilm(f);
        filmVisto.setUtente(u);

        return filmVistoRepo.save(filmVisto);
    }

    @Override
    public boolean deleteFilmVisto(int id) {
        filmVistoRepo.deleteById(id);
        return true;
    }
}
