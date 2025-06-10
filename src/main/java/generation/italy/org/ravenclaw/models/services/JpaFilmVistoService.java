package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaFilmVistoService implements FilmVistoService{
    private FilmVistoRepository filmVistoRepo;
    private FilmRepository filmRepo;
    private UtenteRepository utenteRepo;
    private RecensioneRepository recensioneRepo;

    @Autowired
    public JpaFilmVistoService(FilmVistoRepository filmVistoRepo,
                               FilmRepository filmRepo,
                               UtenteRepository utenteRepo,
                               RecensioneRepository recensioneRepo){
        this.filmVistoRepo = filmVistoRepo;
        this.filmRepo = filmRepo;
        this.utenteRepo = utenteRepo;
        this.recensioneRepo = recensioneRepo;
    }

    @Override
    public List<FilmVisto> findAll() {
        return filmVistoRepo.findAll();
    }

    @Override
    public List<FilmVisto> findByUtente(int utenteId) {
        return filmVistoRepo.findByUtenteUtenteId(utenteId);
    }

    @Override
    public Optional<FilmVisto> findById(int id) {
        return filmVistoRepo.findById(id);
    }

    @Override
    public FilmVisto update(FilmVisto filmVisto, int filmId, int utenteId, int recensioneId) throws EntityNotFoundException {
        return save(filmVisto, filmId, utenteId, recensioneId);
    }

    @Override
    public FilmVisto save(FilmVisto filmVisto, int filmId, int utenteId, int recensioneId) throws EntityNotFoundException {
        Optional<Film> optionalFilm = filmRepo.findById(filmId);
        Optional<Utente> optionalUtente = utenteRepo.findById(utenteId);
        Optional<Recensione> optionalRecensione = recensioneRepo.findById(recensioneId);

        Film f = optionalFilm.orElseThrow(() -> new EntityNotFoundException(Film.class));
        Utente u = optionalUtente.orElseThrow(() -> new EntityNotFoundException(Utente.class));

        if(optionalRecensione.isPresent()){
            Recensione r = optionalRecensione.get();
            filmVisto.setRecensione(r);
        }

        filmVisto.setFilm(f);
        filmVisto.setUtente(u);

        return filmVistoRepo.save(filmVisto);
    }

    @Override
    public boolean delete(int id) {
        filmVistoRepo.deleteById(id);
        return true;
    }
}
