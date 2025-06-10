package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.services.FilmVistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/filmvisto")
public class FilmVistoController {
    private FilmVistoService filmVistoService;

    @Autowired
    public FilmVistoController(FilmVistoService filmVistoService) {
        this.filmVistoService = filmVistoService;
    }
}
