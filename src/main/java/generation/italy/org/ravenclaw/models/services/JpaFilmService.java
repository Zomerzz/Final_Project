package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Film;
import generation.italy.org.ravenclaw.models.repositories.CasaRepository;
import generation.italy.org.ravenclaw.models.repositories.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaFilmService implements FilmService{
    private FilmRepository filmRepo;
    private CasaRepository casaRepo;

    @Autowired
    public JpaFilmService(FilmRepository filmRepo, CasaRepository casaRepo){
        this.filmRepo = filmRepo;
        this.casaRepo = casaRepo;
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
    public Film saveFilm(Film film) {
        return filmRepo.save(film);
    }

    @Override
    public Film updateFilm(Film film) {
        Optional<Film> optFilm = filmRepo.findById(film.getFilmId());
        if(optFilm.isEmpty()){
            throw new EntityNotFoundException(); //AL MOMENTO L'EXCEPTION è QUELLA DI JAKARTA
        }
        return saveFilm(film);
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
}
