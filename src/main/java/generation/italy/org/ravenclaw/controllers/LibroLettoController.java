package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.services.LibroLettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/libroletto")
public class LibroLettoController {
    private LibroLettoService libroLettoService;

    @Autowired
    public LibroLettoController(LibroLettoService libroLettoService){
        this.libroLettoService = libroLettoService;
    }


}
