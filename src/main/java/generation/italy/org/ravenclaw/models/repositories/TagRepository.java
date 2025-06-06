package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag, Integer> {
}
