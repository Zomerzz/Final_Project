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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JpaFilmService implements FilmService {
    private FilmRepository filmRepo;
    private CasaRepository casaRepo;
    private CriteriaFilmRepository criteriaFRepo;
    private FilmVistoRepository filmVistoRepo;
    private UtenteRepository utenteRepo;
    private RecensioneRepository recensioneRepo;
    private TagRepository tagRepo;

    @Autowired
    public JpaFilmService(FilmRepository filmRepo,
                          CasaRepository casaRepo,
                          CriteriaFilmRepository criteriaFRepo,
                          FilmVistoRepository filmVistoRepo,
                          UtenteRepository utenteRepo,
                          RecensioneRepository recensioneRepo,
                          TagRepository tagRepo){
        this.filmRepo = filmRepo;
        this.casaRepo = casaRepo;
        this.criteriaFRepo = criteriaFRepo;
        this.filmVistoRepo = filmVistoRepo;
        this.utenteRepo = utenteRepo;
        this.recensioneRepo = recensioneRepo;
        this.tagRepo = tagRepo;
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
        filmVisto.setRecensione(recensioneRepo.findById(recensioneId).orElseThrow(()-> new EntityNotFoundException(Recensione.class)));
        return saveFilmVisto(filmVisto, filmId, utenteId);
    }

    @Override
    public FilmVisto saveFilmVisto(FilmVisto filmVisto, int filmId, int utenteId) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        Optional<Utente> optionalUtente = utenteRepo.findById(utenteId);
        Optional<Film> optionalFilm = filmRepo.findById(filmId);

        Utente u = optionalUtente.orElseThrow(() -> new EntityNotFoundException(Utente.class));
        Film f = optionalFilm.orElseThrow(() -> new EntityNotFoundException(Utente.class));

        filmVisto.setFilm(f);
        filmVisto.setUtente(u);

        return filmVistoRepo.save(filmVisto);
    }

    @Override
    public boolean deleteFilmVisto(int id) {
        filmVistoRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<FilmVisto> findFilmVistoByFilmIdAndUtenteId(int filmId, int utenteId) {
        return filmVistoRepo.findByUtenteUtenteIdAndFilmFilmId(utenteId, filmId);
    }

    @Override
    public List<Film> findFilmConsigliatiByUtenteId(int utenteId) {
        List<Integer> favouriteTagsIds = tagRepo.findFavouriteTagsByUserId(utenteId);
        var bestFiveTags = favouriteTagsIds.stream().limit(5).toList();
        List<Integer> favouriteGenresIds = tagRepo.findFavouriteGenresByUserId(utenteId);
        var bestFiveGenres = favouriteGenresIds.stream().limit(5).toList();

        var visti = filmRepo.findByUser(utenteId);
        List<Film> all = filmRepo.findAll();
        var consigliati = all.stream().filter(film -> {
                    int counter = 0;
                    int limit = 1;
                    for(Integer t : bestFiveGenres){
                        Set<Integer> filmTagsIds = film.getTagSet().stream().map(Tag::getTagId).collect(Collectors.toSet());
                        if(filmTagsIds.contains(t)){
                            counter ++; //al momento lasciamo il limit a uno ma potremmo aumentare in futuro
                        }
                        if(counter >= limit) {
                            return true;
                        }
                    }
                    return false;
                })
                .filter(film -> {
                    Set<Integer> filmTagsIds = film.getTagSet().stream().map(Tag::getTagId).collect(Collectors.toSet());
                    int counter = 0;
                    int limit = 1;
                    for(Integer t: bestFiveTags){
                        if(filmTagsIds.contains(t)){
                            counter ++; //al momento lasciamo il limit a uno ma potremmo aumentare in futuro
                        }
                        if(counter >= limit) {
                            return true;
                        }
                    }
                    return false;
                })
                .filter(libro -> !visti.contains(libro)).toList();

        return consigliati;
    }

}
