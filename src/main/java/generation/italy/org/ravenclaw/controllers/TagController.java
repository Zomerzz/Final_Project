package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.TagDto;
import generation.italy.org.ravenclaw.models.dtos.UtenteDto;
import generation.italy.org.ravenclaw.models.entities.Tag;
import generation.italy.org.ravenclaw.models.services.LibroService;
import generation.italy.org.ravenclaw.models.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tag")
public class TagController {
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> searchTags(){
        List<Tag> tagList = tagService.findAllTags();
        List<TagDto> tagDto = tagList.stream().map(TagDto::toDto).toList();
        return ResponseEntity.ok(tagDto);
    }

    @GetMapping("/genere/{isGenere}")
    public ResponseEntity<List<TagDto>> searchTagByIsGenere(@PathVariable boolean isGenere){
        List<Tag> tags = tagService.findByIsGenere(isGenere);
        List<TagDto> tagDto = tags.stream().map(TagDto::toDto).toList();
        return ResponseEntity.ok(tagDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById (@PathVariable int id){
        Optional<Tag> tagOpt = tagService.findTagById(id);
        if(tagOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(TagDto.toDto(tagOpt.get()));
    }
    @GetMapping("/{mediaType}/{id}")
    public ResponseEntity<List<TagDto>> searchByIdAndMediaType(@PathVariable String mediaType,@PathVariable int id){
        List<Tag> tagList = tagService.findByMedaIdAndType(id, mediaType);
        return ResponseEntity.ok(tagList.stream().map(TagDto::toDto).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        Optional<Tag> tagOpt = tagService.findTagById(id);
        if(tagOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        boolean deleted = tagService.deleteTag(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> createTag (@RequestBody TagDto tagDto){
        Tag tag = tagDto.toTag();
        Tag savedTag = tagService.saveTag(tag);
        TagDto newTagDto = TagDto.toDto(savedTag);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTagDto.getTagId())
                .toUri();

        return ResponseEntity.created(location).body(newTagDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTag (@PathVariable int id, @RequestBody TagDto tagDto){
        Optional<Tag> tagOpt = tagService.findTagById(id);
        if(id != tagDto.getTagId()){
            return ResponseEntity.badRequest().body("id dto e id del percorso non coincidono");
        }
        if(tagOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Tag tag = tagService.updateTag(tagOpt.get());
        return ResponseEntity.ok(TagDto.toDto(tag));
    }

}
