package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.AutoreDto;
import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.services.AutoreService;
import generation.italy.org.ravenclaw.models.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/autore")
public class AutoreController {
    private AutoreService autoreService;

    @Autowired
    public AutoreController(AutoreService autoreService){
        this.autoreService = autoreService;
    }
    public ResponseEntity<List<AutoreDto>> searchAutore(){//manca da lanciare errore
        List<Autore> autori = autoreService.findAllAutori();
        List<AutoreDto> autoriDto = autori.stream().map(AutoreDto::toDto).toList();
        return ResponseEntity.ok(autoriDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable int id){//manca da lanciare errore
        Optional<Autore> optionalAutore = autoreService.findAutoreById(id);
        if (optionalAutore.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AutoreDto.toDto(optionalAutore.get()));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){//manca da lanciare errore
        boolean deleted = autoreService.deleteAutore(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<AutoreDto> createAutore(@RequestBody AutoreDto autoreDto){//manca da lanciare errore
        Autore autore = autoreDto.toAutore();
        Autore newAut = autoreService.saveAutore(autore);
        AutoreDto newAutoreDto = AutoreDto.toDto(newAut);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAutoreDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(newAutoreDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAutore(@PathVariable int id , @RequestBody AutoreDto autoreDto){//manca da lanciare errore
        Optional<Autore> optionalAutore = autoreService.findAutoreById(id);
        if(optionalAutore.isEmpty()){
            ResponseEntity.notFound();
        }
        if(id != autoreDto.getId()){
            ResponseEntity.badRequest().body("id dto e id del percorso non coincidono");
        }
        Autore autore = autoreService.updateAutore(optionalAutore.get());
        return ResponseEntity.ok(AutoreDto.toDto(autore));
    }
}

