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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<?> searchCase() {
        List<Casa> listCase = casaService.findAll();
        List<CasaDto> caseDto = listCase.stream().map(CasaDto::toDto).toList();
        return ResponseEntity.ok(caseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable int id) {
        Optional<Casa> casaOpt = casaService.findById(id);
        if(casaOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CasaDto.toDto(casaOpt.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Optional<Casa> casaOpt = casaService.findById(id);
        if(casaOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        boolean deleted = casaService.deleteById(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CasaDto> createCasa (@RequestBody CasaDto casaDto){
        Casa casa = casaDto.toCasa();
        Casa savedCasa = casaService.save(casa);
        CasaDto newCasaDto = CasaDto.toDto(savedCasa);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCasaDto.getCasaId() )
                .toUri();

        return ResponseEntity.created(location).body(newCasaDto);
    }

    @PutMapping
    public ResponseEntity<?> updateCasa (@PathVariable int id , @RequestBody UtenteDto utenteDto){
        Optional<Casa> casaOpt = casaService.findById(id);
        if(id != utenteDto.getId()){
            return ResponseEntity.badRequest().body("id dto e id del percorso non coincidono");
        }
        if(casaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Casa casa = casaService.updateCasa(casaOpt.get());
        return ResponseEntity.ok(CasaDto.toDto(casa));
    }

}