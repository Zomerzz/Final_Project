package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.DataException;
import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.FilmDto;
import generation.italy.org.ravenclaw.models.dtos.LibroDto;
import generation.italy.org.ravenclaw.models.entities.Film;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;
import generation.italy.org.ravenclaw.models.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/film")
public class FilmController {
    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<Page<FilmDto>> searchFilm(@RequestParam(required = false) String titolo,
                                                    @RequestParam(required = false) String casaDiProduzione,
                                                    @RequestParam(required = false) String casaDiPubblicazione,
                                                    @RequestParam(required = false) LocalDate dataDiPubblicazione,
                                                    @RequestParam(required = false) LocalDate minData,
                                                    @RequestParam(required = false) LocalDate maxData,
                                                    @RequestParam(required = false) List<Integer> tags,
                                                    @RequestParam(required = false) Integer minVoto,
                                                    @RequestParam(required = false) Integer maxVoto,
                                                    @RequestParam(required = false) String autoreNome,
                                                    @RequestParam(defaultValue = "10") int pageSize,
                                                    @RequestParam(defaultValue = "0") int numPage,
                                                    @RequestParam(defaultValue = "orderByDataPubblicazioneDesc") String sort
    )
    {
        FilmFilterCriteria ffc = new FilmFilterCriteria(titolo, casaDiProduzione,
                casaDiPubblicazione, dataDiPubblicazione, minData, maxData,
                tags, minVoto, maxVoto, autoreNome, pageSize, numPage, sort);
        Page<Film> film = filmService.searchFilm(ffc);
        return ResponseEntity.ok(film.map(FilmDto::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable Integer id) throws DataException {
        Optional<Film> opt = filmService.findFilmById(id);
        return opt.map(film -> ResponseEntity.ok().body(FilmDto.toDto(film)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<FilmDto>> getConsigliati(@PathVariable int utenteId) {
        List<Film> filmConsigliati = filmService.findFilmConsigliatiByUtenteId(utenteId);
        return ResponseEntity.ok(filmConsigliati.stream().map(FilmDto::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<?> addFilm(@RequestBody FilmDto fdto) throws EntityNotFoundException {
        Film film = fdto.toFilm();
        filmService.saveFilm(film, fdto.getCasaDiProduzione().getCasaId(), fdto.getCasaDiPubblicazione().getCasaId());
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
        Optional<Film> opt = filmService.findFilmById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        boolean removed = filmService.deleteFilm(id);
        if(removed){return ResponseEntity.noContent().build();}
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm (@PathVariable int id, @RequestBody FilmDto fdto) throws EntityNotFoundException {
        if(id != fdto.getId()) return ResponseEntity.badRequest().build();

        Optional<Film> oF = filmService.findFilmById(id);
        if(oF.isPresent()){
            Film newFilm = filmService.saveFilm(fdto.toFilm(),
                    fdto.getCasaDiProduzione().getCasaId(),
                    fdto.getCasaDiPubblicazione().getCasaId());
            return ResponseEntity.ok().body(FilmDto.toDto(newFilm));
        }
        return ResponseEntity.badRequest().build();
    }
}
