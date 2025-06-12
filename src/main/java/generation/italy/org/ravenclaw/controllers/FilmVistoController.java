package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.FilmVistoDto;
import generation.italy.org.ravenclaw.models.entities.FilmVisto;
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
        if (filmVisti.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<FilmVistoDto> filmVistoDto = filmVisti.stream().map(FilmVistoDto::toDto).toList();
        return ResponseEntity.ok(filmVistoDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> searchFilmVistoById(@PathVariable int id){
        Optional<FilmVisto> opt = filmService.findFilmVistoById(id);
        return opt.map(ff -> ResponseEntity.ok().body(FilmVistoDto.toDto(ff)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createFilmVisto (@RequestBody FilmVistoDto dto) throws EntityNotFoundException {
        FilmVisto fv = dto.toFilmVisto();
        FilmVisto saved = filmService.saveFilmVisto(fv,
                    dto.getFilm().getFilmId(),
                    dto.getUtenteId(),
                    0);
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
        if(updatedDto.getRecensione() != null) {
            filmVisto = filmService.updateFilmVisto(opt.get(),
                    updatedDto.getFilm().getFilmId(),
                    updatedDto.getUtenteId(),
                    updatedDto.getRecensione().getRecensioneId());
        } else {
            filmVisto = filmService.updateFilmVisto(opt.get(),
                    updatedDto.getFilm().getFilmId(),
                    updatedDto.getUtenteId(),
                    0);
        }
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
