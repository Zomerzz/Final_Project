package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.services.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/recensione")
public class RecensioneController {
    private RecensioneService recensioneService;

    @Autowired
    public RecensioneController(RecensioneService recensioneService){
        this.recensioneService = recensioneService;
    }


}
