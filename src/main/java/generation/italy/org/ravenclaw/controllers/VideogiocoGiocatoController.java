package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.LibroLettoDto;
import generation.italy.org.ravenclaw.models.dtos.VideogiocoGiocatoDto;
import generation.italy.org.ravenclaw.models.entities.LibroLetto;
import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;
import generation.italy.org.ravenclaw.models.services.VideogiocoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/videogioco-giocato")
public class VideogiocoGiocatoController {
    private VideogiocoService videogiocoService;

    public VideogiocoGiocatoController(VideogiocoService videogiocoService){
        this.videogiocoService = videogiocoService;
    }

    @GetMapping
    private ResponseEntity<?> searchVideogiochiGiocatiByUtente(@RequestParam int utenteId){
        List<VideogiocoGiocato> videogiochiGiocati = videogiocoService.findVideogiocoGiocatoByUtente(utenteId);
        List<VideogiocoGiocatoDto> videogiocoGiocatoDto = videogiochiGiocati.stream().map(VideogiocoGiocatoDto::toDto).toList();
        return ResponseEntity.ok(videogiocoGiocatoDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> searchVideogiocoGiocatoById(@PathVariable int id){
        Optional<VideogiocoGiocato> opt = videogiocoService.findVideogiocoGiocatoById(id);
        return opt.map(vg -> ResponseEntity.ok().body(VideogiocoGiocatoDto.toDto(vg)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{videogiocoId}/{utenteId}")
    private ResponseEntity<VideogiocoGiocatoDto> searchIfLibroIsLetto(@PathVariable int videogiocoId, @PathVariable int utenteId) {
        Optional<VideogiocoGiocato> opt = videogiocoService.findVideogiocoGiocatoByVideogiocoIdAndUtenteId(videogiocoId, utenteId);
        return opt.map(giocato -> ResponseEntity.ok(VideogiocoGiocatoDto.toDto(giocato))).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createVideogiocoGiocato (@RequestBody VideogiocoGiocatoDto dto) throws EntityNotFoundException {
        VideogiocoGiocato vg = dto.toVideogiocoGiocato();
        VideogiocoGiocato saved = videogiocoService.saveVideogiocoGiocato(vg,
                                dto.getVideogioco().getId(),
                                dto.getUtenteId());
        VideogiocoGiocatoDto newDto = VideogiocoGiocatoDto.toDto(saved);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getVideogiocoGiocatoId())
                .toUri();

        return ResponseEntity.created(location).body(newDto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateVideogiocoGiocato(@PathVariable int id, @RequestBody VideogiocoGiocatoDto updatedDto) throws EntityNotFoundException {
        if(id != updatedDto.getVideogiocoGiocatoId()){
            return ResponseEntity.badRequest().body("id dto e id del path non coincidono");
        }
        Optional<VideogiocoGiocato> opt = videogiocoService.findVideogiocoGiocatoById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        VideogiocoGiocato videogiocoGiocato = null;
        videogiocoGiocato = videogiocoService.updateVideogiocoGiocato(opt.get(),
                updatedDto.getVideogioco().getId(),
                updatedDto.getUtenteId(),
                updatedDto.getRecensione().getRecensioneId());
        return ResponseEntity.ok(VideogiocoGiocatoDto.toDto(videogiocoGiocato));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteVideogiocoGiocato(@PathVariable int id){
        Optional<VideogiocoGiocato> opt = videogiocoService.findVideogiocoGiocatoById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        videogiocoService.deleteVideogiocoGiocato(id);
        return ResponseEntity.noContent().build();
    }
}
