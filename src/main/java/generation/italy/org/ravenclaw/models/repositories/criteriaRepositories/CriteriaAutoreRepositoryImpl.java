package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.searchCriteria.AutoreFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaAutoreRepositoryImpl implements CriteriaAutoreRepository{
    EntityManager em;

    @Autowired
    public CriteriaAutoreRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Autore> searchAutoreByFilters(AutoreFilterCriteria filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Autore> query = cb.createQuery(Autore.class);
        Root<Autore> root = query.from(Autore.class);
        List<Predicate> predicates = new ArrayList<>();
        if(filters.getNome() != null){

            //array con le varie parole inserite nel campo nome dell'autore
            String[] keywords = filters.getNome().
                    trim().
                    toLowerCase().
                    split("\\s+");

            // il coalesce ci salva dal fatto che i valori potrebbero essere null
            Expression<String> name = cb.coalesce(root.get("nome"), "");
            Expression<String> secondName = cb.coalesce(root.get("secondoNome"), "");
            Expression<String> lastName = cb.coalesce(root.get("cognome"), "");

            //
            Expression<String> fullName = cb.lower(
                    cb.concat(
                            cb.concat(
                                    cb.concat(name, cb.literal(" ")),
                                    secondName
                            ),
                            cb.concat(cb.literal(" "), lastName)
                    )
            );
            for (String kw : keywords) {
                predicates.add(cb.like(fullName, "%" + kw + "%"));
            }
        }
        if(filters.getMinData() != null && filters.getMaxData() != null){
            predicates.add(cb.between(root.get("dataDiNascita"), filters.getMinData(), filters.getMaxData()));
        }
        if(filters.getMinData() != null && filters.getMaxData() == null){
            predicates.add(cb.greaterThan(root.get("dataDiNascita"), filters.getMinData()));
        }
        if(filters.getMinData() == null && filters.getMaxData() != null){
            predicates.add(cb.lessThan(root.get("dataDiNascita"), filters.getMaxData()));
        }
        query.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(query).getResultList();

    }
}
