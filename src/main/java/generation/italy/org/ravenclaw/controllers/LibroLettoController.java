package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.dtos.LibroLettoDto;
import generation.italy.org.ravenclaw.models.entities.LibroLetto;
import generation.italy.org.ravenclaw.models.services.LibroLettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/libroletto")
public class LibroLettoController {
    private LibroLettoService libroLettoService;

    @Autowired
    public LibroLettoController(LibroLettoService libroLettoService){
        this.libroLettoService = libroLettoService;
    }

    @GetMapping
    private ResponseEntity<?> searchVLibriLettiByUtente(@RequestBody int utenteId){
        List<LibroLetto> libriLetti = libroLettoService.findByUtente(utenteId);
        if (libriLetti.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<LibroLettoDto> libroLettoDto = libriLetti.stream().map(LibroLettoDto::toDto).toList();
        return ResponseEntity.ok(libroLettoDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> searchLibroLettoById(@PathVariable int id){
        Optional<LibroLetto> opt = libroLettoService.findById(id);
        return opt.map(ll -> ResponseEntity.ok().body(LibroLettoDto.toDto(ll)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createLibroLetto (@RequestBody LibroLettoDto dto) throws EntityNotFoundException {
        LibroLetto ll = dto.toLibroLetto();
        LibroLetto saved = libroLettoService.save(ll, dto.getLibroId(), dto.getUtenteId(), dto.getRecensioneId());
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
        Optional<LibroLetto> opt = libroLettoService.findById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        LibroLetto libroLetto = libroLettoService.update(opt.get(), updatedDto.getLibroId(), updatedDto.getUtenteId(), updatedDto.getRecensioneId());
        return ResponseEntity.ok(LibroLettoDto.toDto(libroLetto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteLibroLetto(@PathVariable int id){
        Optional<LibroLetto> opt = libroLettoService.findById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        libroLettoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
