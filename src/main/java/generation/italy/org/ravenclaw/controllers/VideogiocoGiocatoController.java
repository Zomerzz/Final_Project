package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.LibroDto;
import generation.italy.org.ravenclaw.models.dtos.VideogiocoGiocatoDto;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;
import generation.italy.org.ravenclaw.models.services.VideogiocoGiocatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<?> searchVideogiochiGiocatiByUtente(@RequestBody int utenteId){
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

}
