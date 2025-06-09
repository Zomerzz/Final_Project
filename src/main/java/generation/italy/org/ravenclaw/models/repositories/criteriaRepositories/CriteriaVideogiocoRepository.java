package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;

import java.util.List;

public interface CriteriaVideogiocoRepository {
    List<Videogioco> searchVideogiocoByFilter(VideogiocoFilterCriteria filters);
}
