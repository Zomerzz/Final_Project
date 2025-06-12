package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.repositories.*;
import generation.italy.org.ravenclaw.models.repositories.criteriaRepositories.CriteriaFilmRepository;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Film saveFilm(Film film, Casa casaProduzione, Casa casaPubblicazione) {
        film.setCasaDiProduzione(casaProduzione);
        film.setCasaDiPubblicazione(casaPubblicazione);
        return filmRepo.save(film);
    }

    @Override
    public boolean deleteFilm(int id) {
        Optional<Film> optFilm = filmRepo.findById(id);
        if(optFilm.isEmpty()){
            throw new EntityNotFoundException(); //AL MOMENTO L'EXCEPTION è QUELLA DI JAKARTA
        }
        filmRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Film> searchFilm(FilmFilterCriteria ffc) {
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
    public FilmVisto updateFilmVisto(FilmVisto filmVisto, Film film, int utenteId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        return saveFilmVisto(filmVisto, film, utenteId, recensione);
    }

    @Override
    public FilmVisto saveFilmVisto(FilmVisto filmVisto, Film film, int utenteId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        Optional<Utente> optionalUtente = utenteRepo.findById(utenteId);

        Utente u = optionalUtente.orElseThrow(() -> new generation.italy.org.ravenclaw.exceptions.EntityNotFoundException(Utente.class));

        if(recensione != null){
            filmVisto.setRecensione(recensione);
        }

        filmVisto.setFilm(film);
        filmVisto.setUtente(u);

        return filmVistoRepo.save(filmVisto);
    }

    @Override
    public boolean deleteFilmVisto(int id) {
        filmVistoRepo.deleteById(id);
        return true;
    }
}
