package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.VideogiocoDto;
import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;
import generation.italy.org.ravenclaw.models.services.VideogiocoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<VideogiocoDto>> filteredSearch(@RequestParam(required = false) String titolo,
                                                              @RequestParam(required = false) String nomeCasaDiProduzione,
                                                              @RequestParam(required = false) String nomeCasaDiPubblicazione,
                                                              @RequestParam(required = false) LocalDate minDataDiPubblicazione,
                                                              @RequestParam(required = false) LocalDate maxDataDiPubblicazione,
                                                              @RequestParam(required = false) Integer minOreDiGiocoStoriaPrincipale,
                                                              @RequestParam(required = false) Integer maxOreDiGiocoStoriaPrincipale,
                                                              @RequestParam(required = false) Integer minVoto,
                                                              @RequestParam(required = false) Integer maxVoto,
                                                              @RequestParam(required = false) List<Integer> tags
                                                              ) {
        VideogiocoFilterCriteria vfc = new VideogiocoFilterCriteria(titolo, nomeCasaDiProduzione, nomeCasaDiPubblicazione, minDataDiPubblicazione, maxDataDiPubblicazione, minOreDiGiocoStoriaPrincipale,maxOreDiGiocoStoriaPrincipale,minVoto,maxVoto,tags);
        List<Videogioco> videogiochi = videogiocoService.searchVideogiochi(vfc);
        if(videogiochi.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(videogiochi.stream().map(VideogiocoDto::toDto).toList());
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
