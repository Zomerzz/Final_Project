package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.RecensioneDto;
import generation.italy.org.ravenclaw.models.dtos.TagDto;
import generation.italy.org.ravenclaw.models.entities.Recensione;
import generation.italy.org.ravenclaw.models.entities.Tag;
import generation.italy.org.ravenclaw.models.services.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/recensione")
public class RecensioneController {
    private RecensioneService recensioneService;

    @Autowired
    public RecensioneController(RecensioneService recensioneService){
        this.recensioneService = recensioneService;
    }
    @GetMapping
    public ResponseEntity<List<RecensioneDto>> searchRecensione() {
        List<Recensione> recensioneList = recensioneService.findAllRecensioni();
        if (recensioneList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<RecensioneDto> recensioneDto = recensioneList.stream().map(RecensioneDto::toDto).toList();
        return ResponseEntity.ok(recensioneDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable int id){
        Optional<Recensione> recensioneOptional = recensioneService.findRecensioneById(id);
        if(recensioneOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(RecensioneDto.toDto(recensioneOptional.get()));
    }

    @GetMapping("/film-id={filmId}")
    public ResponseEntity<?> searchByFilmId(@PathVariable int filmId){
        List<Recensione> recensioni = recensioneService.findRecensioneByFilmId(filmId);
        if(recensioni.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recensioni.stream().map(RecensioneDto::toDto));
    }

    @GetMapping("/libro-id={libroId}")
    public ResponseEntity<?> searchByLibroId(@PathVariable int libroId){
        List<Recensione> recensioni = recensioneService.findRecensioneByLibroId(libroId);
        if(recensioni.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recensioni.stream().map(RecensioneDto::toDto));
    }

    @GetMapping("/videogioco-id={videogiocoId}")
    public ResponseEntity<?> searchByVideogiocoId(@PathVariable int videogiocoId){
        List<Recensione> recensioni = recensioneService.findRecensioneByVideogiocoId(videogiocoId);
        if(recensioni.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recensioni.stream().map(RecensioneDto::toDto));
    }
//
     //ANCORA DA DECIDERE COME GESTIRE LE RECENSIONI DI UN DATO UTENTE
//    @GetMapping("/utente-id={utenteId}")
//    public ResponseEntity<?> searchByUtenteId(@PathVariable int utenteId){
//        List<Recensione> recensioni = recensioneService.findRecensioneByUtenteId(utenteId);
//        if(recensioni.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(recensioni.stream().map(RecensioneDto::toDto));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        Optional<Recensione> recensioneOptional = recensioneService.findRecensioneById(id);
        if(recensioneOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        boolean deleted = recensioneService.deleteRecensione(id);
        if(!deleted){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> createRecensione (@RequestBody RecensioneDto recensioneDto){
        Recensione recensione = recensioneDto.toRecensione();
        Recensione savedRecensione = recensioneService.saveRecensione(recensione);
        RecensioneDto newRecensioneDto = RecensioneDto.toDto(savedRecensione);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRecensioneDto.getRecensioneId())
                .toUri();

        return ResponseEntity.created(location).body(newRecensioneDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecensione (@PathVariable int id, @RequestBody RecensioneDto recensioneDto){
        Optional<Recensione> recensioneOptional = recensioneService.findRecensioneById(id);
        if(id != recensioneDto.getRecensioneId()){
            return ResponseEntity.badRequest().body("id dto e id del percorso non coincidono");
        }
        if(recensioneOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Recensione recensione = recensioneService.updateRecensione(recensioneOptional.get());
        return ResponseEntity.ok(RecensioneDto.toDto(recensione));
    }
}
