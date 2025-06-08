package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.CasaDto;
import generation.italy.org.ravenclaw.models.dtos.UtenteDto;
import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Utente;
import generation.italy.org.ravenclaw.models.services.CasaService;
import generation.italy.org.ravenclaw.models.services.LibroService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> searchCase() throws DataException, EntityNotFoundException {
        List<Casa> listCase = casaService.findAll();
        if(listCase.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<CasaDto> caseDto = listCase.stream().map(CasaDto::toDto).toList();
        return ResponseEntity.ok(caseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable int id) throws EntityNotFoundException {
        Optional<Casa> casaOpt = casaService.findById(id);
        if(casaOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CasaDto.toDto(casaOpt.get()));
    }

}