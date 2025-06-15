package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.FilmVistoDto;
import generation.italy.org.ravenclaw.models.dtos.LibroLettoDto;
import generation.italy.org.ravenclaw.models.entities.FilmVisto;
import generation.italy.org.ravenclaw.models.entities.LibroLetto;
import generation.italy.org.ravenclaw.models.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/film-visto")
public class FilmVistoController {
    private FilmService filmService;

    @Autowired
    public FilmVistoController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    private ResponseEntity<?> searchFilmVistiByUtente(@RequestParam int utenteId){
        List<FilmVisto> filmVisti = filmService.findFilmVistoByUtente(utenteId);
        List<FilmVistoDto> filmVistoDto = filmVisti.stream().map(FilmVistoDto::toDto).toList();
        return ResponseEntity.ok(filmVistoDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> searchFilmVistoById(@PathVariable int id){
        Optional<FilmVisto> opt = filmService.findFilmVistoById(id);
        return opt.map(ff -> ResponseEntity.ok().body(FilmVistoDto.toDto(ff)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{filmId}/{utenteId}")
    private ResponseEntity<FilmVistoDto> searchIfLibroIsLetto(@PathVariable int filmId, @PathVariable int utenteId) {
        Optional<FilmVisto> opt = filmService.findFilmVistoByFilmIdAndUtenteId(filmId, utenteId);
        return opt.map(visto -> ResponseEntity.ok(FilmVistoDto.toDto(visto))).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createFilmVisto (@RequestBody FilmVistoDto dto) throws EntityNotFoundException {
        FilmVisto fv = dto.toFilmVisto();
        FilmVisto saved = filmService.saveFilmVisto(fv,
                    dto.getFilm().getId(),
                    dto.getUtenteId());
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
        Optional<FilmVisto> opt = filmService.findFilmVistoById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        FilmVisto filmVisto = null;
        filmVisto = filmService.updateFilmVisto(opt.get(),
                updatedDto.getFilm().getId(),
                updatedDto.getUtenteId(),
                updatedDto.getRecensione().getRecensioneId());
        return ResponseEntity.ok(FilmVistoDto.toDto(filmVisto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteFilmVisto(@PathVariable int id){
        Optional<FilmVisto> opt = filmService.findFilmVistoById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        filmService.deleteFilmVisto(id);
        return ResponseEntity.noContent().build();
    }
}
