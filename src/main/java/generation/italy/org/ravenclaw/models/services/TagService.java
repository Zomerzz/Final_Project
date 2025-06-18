package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Optional<Tag> findTagById(int id);

    List<Tag> findAllTags();

    Tag saveTag(Tag tag);

    Tag updateTag(Tag tag);

    boolean deleteTag(int id);

    List<Tag> findByIsGenere(boolean isGenere);

    List<Tag> findByMedaIdAndType(int id, String mediaType);
}
