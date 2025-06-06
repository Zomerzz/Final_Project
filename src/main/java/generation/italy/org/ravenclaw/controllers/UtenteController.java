package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.services.TagService;
import generation.italy.org.ravenclaw.models.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {
    private UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService){
        this.utenteService = utenteService;
    }
}