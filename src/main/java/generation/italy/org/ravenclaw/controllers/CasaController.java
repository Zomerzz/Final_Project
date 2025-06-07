package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.CasaDto;
import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.services.CasaService;
import generation.italy.org.ravenclaw.models.services.LibroService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/casa")
public class CasaController {
    private CasaService casaService;

    @Autowired
    public CasaController(CasaService casaService){
        this.casaService = casaService;
    }

    @GetMapping
    public ResponseEntity<?> findCasa() throws DataException {
        List<CasaDto> listCasa = casaService.findAll().stream().map(CasaDto::toDto).toList();
        return ResponseEntity.ok(listCasa);
    }
}