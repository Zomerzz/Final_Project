package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Film;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;

import java.util.List;

public interface CriteriaFilmRepository {
    List<Film> searchFilmByFilters(FilmFilterCriteria filmFilters);
}
