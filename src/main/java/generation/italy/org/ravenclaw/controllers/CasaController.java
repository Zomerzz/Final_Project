package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.services.CasaService;
import generation.italy.org.ravenclaw.models.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/casa")
public class CasaController {
    private CasaService casaService;

    @Autowired
    public CasaController(CasaService casaService){
        this.casaService = casaService;
    }
}