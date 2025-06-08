package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.FilmDto;
import generation.italy.org.ravenclaw.models.entities.Film;
import generation.italy.org.ravenclaw.models.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/film")
public class FilmController {
    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<?> searchFilms(){
        List<FilmDto> result = filmService.findAllFilms().stream().map(FilmDto::toDto).toList();
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addFilm(@RequestBody FilmDto fdto){
        Film film = fdto.toFilm();
        filmService.saveFilm(film,fdto.getCasaDiProduzione(),fdto.getCasaDiPubblicazione());
        FilmDto newFilm = FilmDto.toDto(film);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFilm.getId())
                .toUri();
        return ResponseEntity.created(location).body(newFilm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById( @PathVariable int id){
        boolean removed = filmService.deleteFilm(id);
        if(removed){return ResponseEntity.noContent().build();}
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm (@PathVariable int id, @RequestBody FilmDto fdto){
        if(id != fdto.getId()) return ResponseEntity.badRequest().build();

        Optional<Film> oF = filmService.findFilmById(id);
        if(oF.isPresent()){
            Film newFilm = filmService.saveFilm(fdto.toFilm(),fdto.getCasaDiProduzione(),fdto.getCasaDiPubblicazione());
            return ResponseEntity.ok().body(FilmDto.toDto(newFilm));
        }
        return ResponseEntity.badRequest().build();
    }
}
