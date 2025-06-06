package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.services.FilmService;
import generation.italy.org.ravenclaw.models.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/libro")
public class LibroController {
    private LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService){
        this.libroService = libroService;
    }
}
