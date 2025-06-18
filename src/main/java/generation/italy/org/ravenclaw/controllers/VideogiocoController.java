package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.DataException;
import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.FilmDto;
import generation.italy.org.ravenclaw.models.dtos.VideogiocoDto;
import generation.italy.org.ravenclaw.models.entities.Film;
import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;
import generation.italy.org.ravenclaw.models.services.VideogiocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/videogioco")
public class VideogiocoController {
    private VideogiocoService videogiocoService;

    @Autowired
    public VideogiocoController(VideogiocoService videogiocoService){
        this.videogiocoService = videogiocoService;
    }

    @GetMapping
    public ResponseEntity<Page<VideogiocoDto>> filteredSearch(@RequestParam(required = false) String titolo,
                                                              @RequestParam(required = false) String nomeCasaDiProduzione,
                                                              @RequestParam(required = false) String nomeCasaDiPubblicazione,
                                                              @RequestParam(required = false) LocalDate minDataDiPubblicazione,
                                                              @RequestParam(required = false) LocalDate maxDataDiPubblicazione,
                                                              @RequestParam(required = false) Integer minOreDiGiocoStoriaPrincipale,
                                                              @RequestParam(required = false) Integer maxOreDiGiocoStoriaPrincipale,
                                                              @RequestParam(required = false) Integer minVoto,
                                                              @RequestParam(required = false) Integer maxVoto,
                                                              @RequestParam(required = false) List<Integer> tags,
                                                              @RequestParam(defaultValue = "10") int pageSize,
                                                              @RequestParam(defaultValue = "0") int numPage,
                                                              @RequestParam(defaultValue = "orderByDataPubblicazioneDesc") String sort //vedi nel model frontend i metodi di sorting
                                                              ) {
        VideogiocoFilterCriteria vfc = new VideogiocoFilterCriteria(titolo, nomeCasaDiProduzione,
                nomeCasaDiPubblicazione, minDataDiPubblicazione, maxDataDiPubblicazione,
                minOreDiGiocoStoriaPrincipale,maxOreDiGiocoStoriaPrincipale,minVoto,maxVoto,tags, pageSize,
                numPage, sort);
        Page<Videogioco> videogiochi = videogiocoService.searchVideogiochi(vfc);
        return ResponseEntity.ok(videogiochi.map(VideogiocoDto::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable Integer id) throws DataException {
        Optional<Videogioco> opt = videogiocoService.findVideogiocoById(id);
        return opt.map(videogioco -> ResponseEntity.ok().body(VideogiocoDto.toDto(videogioco)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<VideogiocoDto>> getConsigliati(@PathVariable int utenteId) {
        List<Videogioco> videogiochiConsigliati = videogiocoService.findVideogiochiConsigliatiByUtenteId(utenteId);
        return ResponseEntity.ok(videogiochiConsigliati.stream().map(VideogiocoDto::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<?> saveGioco (@RequestBody VideogiocoDto vdto) throws EntityNotFoundException {
        Videogioco v = vdto.toVideogioco();
        videogiocoService.saveVideogioco(v,vdto.
                getCasaDiProduzione().getCasaId(),
                vdto.getCasaDiPubblicazione().getCasaId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(v.getVideogiocoId())
                .toUri();
        return ResponseEntity.created(location).body(VideogiocoDto.toDto(v));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGioco (@PathVariable int id){
        boolean deleted = videogiocoService.deleteVideogiocoById(id);
        if(deleted){return ResponseEntity.noContent().build();}
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGioco(@PathVariable int id, @RequestBody VideogiocoDto vdto) throws EntityNotFoundException {
        if(id != vdto.getId()){return ResponseEntity.badRequest().build();}
        Optional<Videogioco> ov = videogiocoService.findVideogiocoById(id);
        if(ov.isEmpty()){return ResponseEntity.notFound().build();}
        Videogioco newGioco = videogiocoService.saveVideogioco(vdto.toVideogioco(),
                vdto.getCasaDiPubblicazione().getCasaId(),
                vdto.getCasaDiProduzione().getCasaId());
        return ResponseEntity.ok().body(VideogiocoDto.toDto(newGioco));
    }
}
