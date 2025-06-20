package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.DataException;
import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.LibroDto;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import generation.italy.org.ravenclaw.models.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/libro")
public class  LibroController {
    private LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService){
        this.libroService = libroService;
    }

    //DEVO AGGIUNGERE lo status NOTFOUND o al max torna la lista vuota?
    @GetMapping
    public ResponseEntity<Page<LibroDto>> searchLibri(@RequestParam(required = false) String titolo,
                                                      @RequestParam(required = false) Integer numeroPagine,
                                                      @RequestParam(required = false) String autoreNome,
                                                      @RequestParam(required = false) String casaEditriceNome,
                                                      @RequestParam(required = false) LocalDate minData,
                                                      @RequestParam(required = false) LocalDate maxData,
                                                      @RequestParam(required = false) Integer minVoto,
                                                      @RequestParam(required = false) Integer maxVoto,
                                                      @RequestParam(required = false) List<Integer> tags,
                                                      @RequestParam(defaultValue = "10") int pageSize,
                                                      @RequestParam(defaultValue = "0") int numPage,
                                                      @RequestParam(defaultValue = "orderByDataPubblicazioneDesc") String sort) {
        LibroFilterCriteria lfc = new LibroFilterCriteria(titolo, numeroPagine, autoreNome, casaEditriceNome, minData, maxData, minVoto, maxVoto, tags,pageSize,numPage,sort);
        Page<Libro> libri = libroService.searchLibro(lfc);
        return ResponseEntity.ok(libri.map(LibroDto::toDto));
    }

    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<LibroDto>> getConsigliati(@PathVariable int utenteId) {
        List<Libro> libriConsigliati = libroService.findLibriConsigliatiByUserId(utenteId);
        return ResponseEntity.ok(libriConsigliati.stream().map(LibroDto::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable Integer id) throws DataException {
        Optional<Libro> opt = libroService.findLibroById(id);
        return opt.map(libro -> ResponseEntity.ok().body(LibroDto.toDto(libro)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<URI> createLibro(@RequestBody LibroDto libroDto) throws DataException, EntityNotFoundException {
        Libro libro = libroDto.toLibro();
        Libro newLibro = libroService.saveLibro(libro, libroDto.getCasaEditrice().getCasaId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newLibro.getLibroId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLibro(@PathVariable int id, @RequestBody LibroDto updatedDto) throws DataException, EntityNotFoundException {
        if(id != updatedDto.getId()){
            return ResponseEntity.badRequest().body("Id del path e Id del dto non corrispondono");
        }
        Optional<Libro> opt = libroService.findLibroById(id);
        if (opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Libro updatedLibro = libroService.updateLibro(opt.get(), updatedDto.getCasaEditrice().getCasaId());
        return ResponseEntity.ok(LibroDto.toDto(updatedLibro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) throws DataException, EntityNotFoundException {
        Optional<Libro> opt = libroService.findLibroById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        libroService.deleteLibro(id);
        return ResponseEntity.noContent().build();
    }
}