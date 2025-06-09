package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.VideogiocoDto;
import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.services.VideogiocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
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
    public ResponseEntity<?> searchGiochi(){
        List<VideogiocoDto> giochiList= videogiocoService.findAll().stream().map(VideogiocoDto::toDto).toList();
        if(giochiList.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok().body(giochiList);
    }

    @PostMapping
    public ResponseEntity<?> saveGioco (@RequestBody VideogiocoDto vdto){
        Videogioco v = vdto.toVideogioco();
        videogiocoService.save(v,vdto.getCasaDiProduzioneId(),vdto.getCasaDiPubblicazioneId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(v.getVideogiocoId())
                .toUri();
        return ResponseEntity.created(location).body(VideogiocoDto.toDto(v));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGioco (@PathVariable int id){
        boolean deleted = videogiocoService.deleteById(id);
        if(deleted){return ResponseEntity.noContent().build();}
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGioco(@PathVariable int id, @RequestBody VideogiocoDto vdto){
        if(id != vdto.getId()){return ResponseEntity.badRequest().build();}
        Optional<Videogioco> ov = videogiocoService.findById(id);
        if(ov.isEmpty()){return ResponseEntity.notFound().build();}
        Videogioco newGioco = videogiocoService.save(vdto.toVideogioco(), vdto.getCasaDiPubblicazioneId(), vdto.getCasaDiProduzioneId());
        return ResponseEntity.ok().body(VideogiocoDto.toDto(newGioco));
    }
}
