package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Film;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CriteriaFilmRepository {
    Page<Film> searchFilmByFilters(FilmFilterCriteria filmFilters);
}
