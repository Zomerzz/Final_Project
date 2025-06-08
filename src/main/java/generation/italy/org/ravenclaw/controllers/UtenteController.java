package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.UtenteDto;
import generation.italy.org.ravenclaw.models.entities.Utente;
import generation.italy.org.ravenclaw.models.services.UtenteService;
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
    public ResponseEntity<List<UtenteDto>> searchUtenti() {
        List<Utente> utenti = utenteService.findAllUtenti();
        if (utenti.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<UtenteDto> utentiDto = utenti.stream().map(UtenteDto::toDto).toList();
        return ResponseEntity.ok(utentiDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable int id) {
        Optional<Utente> utenteOpt = utenteService.findUtenteById(id);
        if(utenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UtenteDto.toDto(utenteOpt.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        Optional<Utente> optUtente = utenteService.findUtenteById(id);
        if(optUtente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
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
                .buildAndExpand(newDto.getId() )
                .toUri();

        return ResponseEntity.created(location).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUtente(@PathVariable int id , @RequestBody UtenteDto utenteDto) {
        Optional<Utente> utenteOpt = utenteService.findUtenteById(id);
        if(id != utenteDto.getId()){
            ResponseEntity.badRequest().body("id dto e id del percorso non coincidono");
        }
        if(utenteOpt.isEmpty()){
            ResponseEntity.notFound().build();
        }

        Utente utente = utenteService.updateUtente(utenteOpt.get());
        return ResponseEntity.ok(UtenteDto.toDto(utente));
    }

}
