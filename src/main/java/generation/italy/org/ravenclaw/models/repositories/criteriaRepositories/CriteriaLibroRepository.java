
package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import org.springframework.data.domain.Page;

public interface CriteriaLibroRepository {
    Page<Libro> searchLibroByFilters(LibroFilterCriteria filters);
}

