
package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;

import java.util.List;

public interface CriteriaLibroRepository {
    List<Libro> searchLibroByFilters(LibroFilterCriteria filters);
}

