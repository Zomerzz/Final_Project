package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.AutoreDto;
import generation.italy.org.ravenclaw.models.dtos.LibroDto;
import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.searchCriteria.AutoreFilterCriteria;
import generation.italy.org.ravenclaw.models.services.AutoreService;
import generation.italy.org.ravenclaw.models.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/autore")
public class AutoreController {
    private AutoreService autoreService;

    @Autowired
    public AutoreController(AutoreService autoreService){
        this.autoreService = autoreService;
    }

    @GetMapping
    public ResponseEntity<List<AutoreDto>> searchAutori(@RequestParam(required = false) String nome,
                                                        @RequestParam(required = false) LocalDate minData,
                                                        @RequestParam(required = false) LocalDate maxData){

        AutoreFilterCriteria aufc = new AutoreFilterCriteria(nome, minData, maxData);
        List<Autore> autori = autoreService.searchCriteria(aufc);
        if(autori.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autori.stream().map(AutoreDto::toDto).toList());

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
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Optional<Autore> optUtente = autoreService.findAutoreById(id);
        if(optUtente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        boolean deleted = autoreService.deleteAutore(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<AutoreDto> createAutore(@RequestBody AutoreDto autoreDto){
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
    public ResponseEntity<?> updateAutore(@PathVariable int id , @RequestBody AutoreDto autoreDto)  {
        Optional<Autore> optionalAutore = autoreService.findAutoreById(id);
        if(id != autoreDto.getId()){
            return ResponseEntity.badRequest().body("id dto e id del percorso non coincidono");
        }
        if(optionalAutore.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Autore autore = autoreService.updateAutore(optionalAutore.get());
        return ResponseEntity.ok(autoreDto);
    }


}

