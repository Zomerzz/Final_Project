package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.AutoreDto;
import generation.italy.org.ravenclaw.models.dtos.UtenteDto;
import generation.italy.org.ravenclaw.models.entities.Utente;
import generation.italy.org.ravenclaw.models.services.TagService;
import generation.italy.org.ravenclaw.models.services.UtenteService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {
    private UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService){
        this.utenteService = utenteService;
    }

    @GetMapping
    public ResponseEntity<List<Utente>> searchUtenti(){
        List<Utente> utenti = utenteService.findAllUtenti();
        return ResponseEntity.ok(utenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable int id){
        Optional<Utente> utenteOpt = utenteService.findUtenteById(id);
        if(utenteOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UtenteDto.toDto(utenteOpt.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        boolean deleted = utenteService.deleteUtente(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UtenteDto> createUtente (@RequestBody UtenteDto utenteDto){
        Utente utente = utenteDto.toUtente();
        Utente savedUtente = utenteService.saveUtente(utente);
        UtenteDto newDto = UtenteDto.toDto(savedUtente);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getUtenteId() )
                .toUri();

        return ResponseEntity.created(location).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUtente(@PathVariable int id , @RequestBody UtenteDto utenteDto){
        Optional<Utente> utenteOpt = utenteService.findUtenteById(id);
        if(utenteOpt.isEmpty()){
            ResponseEntity.notFound();
        }
        if(id != utenteDto.getUtenteId()){
            ResponseEntity.badRequest().body("id dto e id del percorso non coincidono");
        }
        Utente utente = utenteService.updateUtente(utenteOpt.get());
        return ResponseEntity.ok(UtenteDto.toDto(utente));
    }

}

