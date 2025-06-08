package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.searchCriteria.AutoreFilterCriteria;

import java.util.List;

public interface CriteriaAutoreRepository {
    List<Autore> searchAutoreByFilters(AutoreFilterCriteria afc);
}
