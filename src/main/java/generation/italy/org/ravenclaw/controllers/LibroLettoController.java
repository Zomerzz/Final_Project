package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.LibroLettoDto;
import generation.italy.org.ravenclaw.models.entities.LibroLetto;
import generation.italy.org.ravenclaw.models.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/libro-letto")
public class LibroLettoController {
    private LibroService libroService;

    @Autowired
    public LibroLettoController(LibroService libroService){
        this.libroService = libroService;
    }

    @GetMapping
    private ResponseEntity<?> searchVLibriLettiByUtente(@RequestParam int utenteId){
        List<LibroLetto> libriLetti = libroService.findLibroLettoByUtente(utenteId);
        List<LibroLettoDto> libroLettoDto = libriLetti.stream().map(LibroLettoDto::toDto).toList();
        return ResponseEntity.ok(libroLettoDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> searchLibroLettoById(@PathVariable int id){
        Optional<LibroLetto> opt = libroService.findLibroLettoById(id);
        return opt.map(ll -> ResponseEntity.ok().body(LibroLettoDto.toDto(ll)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createLibroLetto (@RequestBody LibroLettoDto dto) throws EntityNotFoundException {
        LibroLetto ll = dto.toLibroLetto();
        LibroLetto saved = libroService.saveLibroLetto(ll,
                    dto.getLibro().getId(),
                    dto.getUtenteId());
        LibroLettoDto newDto = LibroLettoDto.toDto(saved);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getLibroLettoId())
                .toUri();

        return ResponseEntity.created(location).body(newDto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateLibroLetto(@PathVariable int id, @RequestBody LibroLettoDto updatedDto) throws EntityNotFoundException {
        if(id != updatedDto.getLibroLettoId()){
            return ResponseEntity.badRequest().body("id dto e id del path non coincidono");
        }
        Optional<LibroLetto> opt = libroService.findLibroLettoById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        LibroLetto libroLetto = libroService.updateLibroLetto(opt.get(),
                updatedDto.getLibro().getId(),
                updatedDto.getUtenteId(),
                updatedDto.getRecensione().getRecensioneId());

        return ResponseEntity.ok(LibroLettoDto.toDto(libroLetto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteLibroLetto(@PathVariable int id){
        Optional<LibroLetto> opt = libroService.findLibroLettoById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        libroService.deleteLibroLetto(id);
        return ResponseEntity.noContent().build();
    }

}
