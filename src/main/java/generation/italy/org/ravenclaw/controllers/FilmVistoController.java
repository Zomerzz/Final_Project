package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.FilmVistoDto;
import generation.italy.org.ravenclaw.models.entities.FilmVisto;
import generation.italy.org.ravenclaw.models.services.FilmVistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/filmvisto")
public class FilmVistoController {
    private FilmVistoService filmVistoService;

    @Autowired
    public FilmVistoController(FilmVistoService filmVistoService) {
        this.filmVistoService = filmVistoService;
    }

    @GetMapping
    private ResponseEntity<?> searchVFilmVistiByUtente(@RequestParam int utenteId){
        List<FilmVisto> filmVisti = filmVistoService.findByUtente(utenteId);
        if (filmVisti.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<FilmVistoDto> filmVistoDto = filmVisti.stream().map(FilmVistoDto::toDto).toList();
        return ResponseEntity.ok(filmVistoDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> searchFilmVistoById(@PathVariable int id){
        Optional<FilmVisto> opt = filmVistoService.findById(id);
        return opt.map(ff -> ResponseEntity.ok().body(FilmVistoDto.toDto(ff)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createFilmVisto (@RequestBody FilmVistoDto dto) throws EntityNotFoundException {
        FilmVisto fv = dto.toFilmVisto();
        FilmVisto saved = filmVistoService.save(fv, dto.getFilmId(), dto.getUtenteId(), dto.getRecensioneId());
        FilmVistoDto newDto = FilmVistoDto.toDto(saved);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getFilmVistoId())
                .toUri();

        return ResponseEntity.created(location).body(newDto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateFilmVisto(@PathVariable int id, @RequestBody FilmVistoDto updatedDto) throws EntityNotFoundException {
        if(id != updatedDto.getFilmVistoId()){
            return ResponseEntity.badRequest().body("id dto e id del path non coincidono");
        }
        Optional<FilmVisto> opt = filmVistoService.findById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        FilmVisto filmVisto = filmVistoService.update(opt.get(), updatedDto.getFilmId(), updatedDto.getUtenteId(), updatedDto.getRecensioneId());
        return ResponseEntity.ok(FilmVistoDto.toDto(filmVisto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteFilmVisto(@PathVariable int id){
        Optional<FilmVisto> opt = filmVistoService.findById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        filmVistoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
