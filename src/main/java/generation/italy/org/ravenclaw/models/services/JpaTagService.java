package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Recensione;
import generation.italy.org.ravenclaw.models.entities.Tag;
import generation.italy.org.ravenclaw.models.repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaTagService implements TagService{
    private TagRepository tagRepo;

    @Autowired
    public JpaTagService(TagRepository tagRepo){
        this.tagRepo = tagRepo;
    }

    @Override
    public Optional<Tag> findTagById(int id){
        return tagRepo.findById(id);
    }

    @Override
    public List<Tag> findAllTags(){
        return tagRepo.findAll();
    }

    @Override
    public Tag saveTag(Tag tag){
        return tagRepo.save(tag);
    }

    @Override
    public Tag updateTag(Tag tag){
        return tagRepo.save(tag);
    }

    @Override
    public boolean deleteTag(int id){
        tagRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Tag> findByIsGenere(boolean isGenere){
        return tagRepo.findByIsGenere(isGenere);
    }

    @Override
    public List<Tag> findByMedaIdAndType(int id, String mediaType) {
        List<Tag> tagList = switch (mediaType) {
            case "films" -> tagRepo.findByFilmId(id);
            case "libro" -> tagRepo.findByLibroId(id);
            case "videogiochi" -> tagRepo.findByVideogiocoId(id);
            default -> new ArrayList<>();
        };
        return tagList;
    }


}
