package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.LibroDto;
import generation.italy.org.ravenclaw.models.dtos.TagDto;
import generation.italy.org.ravenclaw.models.dtos.VideogiocoGiocatoDto;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.entities.Tag;
import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;
import generation.italy.org.ravenclaw.models.services.VideogiocoGiocatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/videogiocogiocato")
public class VideogiocoGiocatoController {
    private VideogiocoGiocatoService videogiocoGiocatoService;

    public VideogiocoGiocatoController(VideogiocoGiocatoService videogiocoGiocatoService){
        this.videogiocoGiocatoService = videogiocoGiocatoService;
    }

    @GetMapping
    private ResponseEntity<?> searchVideogiochiGiocatiByUtente(@RequestParam int utenteId){
        List<VideogiocoGiocato> videogiochiGiocati = videogiocoGiocatoService.findByUtente(utenteId);
        if (videogiochiGiocati.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<VideogiocoGiocatoDto> videogiocoGiocatoDto = videogiochiGiocati.stream().map(VideogiocoGiocatoDto::toDto).toList();
        return ResponseEntity.ok(videogiocoGiocatoDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> searchVideogiocoGiocatoById(@PathVariable int id){
        Optional<VideogiocoGiocato> opt = videogiocoGiocatoService.findById(id);
        return opt.map(vg -> ResponseEntity.ok().body(VideogiocoGiocatoDto.toDto(vg)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createVideogiocoGiocato (@RequestBody VideogiocoGiocatoDto dto) throws EntityNotFoundException {
        VideogiocoGiocato vg = dto.toVideogiocoGiocato();
        VideogiocoGiocato saved = videogiocoGiocatoService.save(vg, dto.getVideogiocoId(), dto.getUtenteId(), dto.getRecensioneId());
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
        Optional<VideogiocoGiocato> opt = videogiocoGiocatoService.findById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        VideogiocoGiocato videogiocoGiocato = videogiocoGiocatoService.update(opt.get(), updatedDto.getVideogiocoId(), updatedDto.getUtenteId(), updatedDto.getRecensioneId());
        return ResponseEntity.ok(VideogiocoGiocatoDto.toDto(videogiocoGiocato));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteVideogiocoGiocato(@PathVariable int id){
        Optional<VideogiocoGiocato> opt = videogiocoGiocatoService.findById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        videogiocoGiocatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
