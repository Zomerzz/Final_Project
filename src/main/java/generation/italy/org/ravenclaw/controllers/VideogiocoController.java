package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.services.VideogiocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/videogioco")
public class VideogiocoController {
    private VideogiocoService videogiocoService;

    @Autowired
    public VideogiocoController(VideogiocoService videogiocoService){
        this.videogiocoService = videogiocoService;
    }










}
