package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/film")
public class FilmController {
    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }
}
