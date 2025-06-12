package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CriteriaVideogiocoRepository {
    Page<Videogioco> searchVideogiocoByFilter(VideogiocoFilterCriteria filters);
}
