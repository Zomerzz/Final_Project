package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.repositories.FilmVistoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaFilmVistoService implements FilmVistoService{
    private FilmVistoRepository filmVistoRepo;

    @Autowired
    public JpaFilmVistoService(FilmVistoRepository filmVistoRepo){
        this.filmVistoRepo = filmVistoRepo;
    }
}
