package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.entities.Tag;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaLibroRepositoryImpl implements CriteriaLibroRepository{
    private EntityManager em;

    @Autowired
    public CriteriaLibroRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Libro> searchLibroByFilters(LibroFilterCriteria filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Libro> query = cb.createQuery(Libro.class);
        Root<Libro> root = query.from(Libro.class);
        List<Predicate> predicates = new ArrayList<>();

        if(filters.getTitolo() != null){
            predicates.add(cb.like(root.get("titolo"), "%" + filters.getTitolo() + "%"));
        }
        if(filters.getNumeroPagine() != null){
            predicates.add(cb.lessThanOrEqualTo(root.get("numeroPagine"), filters.getNumeroPagine()));
        }
        if(filters.getAutoreId() != null){
            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<Libro> subqueryLibro = subquery.from(Libro.class);
            Join<Libro, Autore> subqueryAutore = subqueryLibro.join("autoreSet");

            // Select the Libro ID where one of their autore matches
            subquery.select(subqueryLibro.get("libroId")).where(
                    cb.equal(subqueryAutore.get("autoreId"), filters.getAutoreId()));

            // Filter by Libro that match one of the Libro found in the subquery
            predicates.add(cb.in(root.get("libroId")).value(subquery));
        }
        //LA RICERCA PER NOME CERCA O PER NOME O PER COGNOME PARTENDO DA UN UNICO INPUT, FORSE MEGLIO DIVIDERLA IN NOME E COGNOME SEPARATI
        if(filters.getAutoreNome() != null){
            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<Libro> subqueryLibro = subquery.from(Libro.class);
            Join<Libro, Autore> subqueryAutore = subqueryLibro.join("autoreSet");

            Predicate nome = cb.like(subqueryAutore.get("nome"), "%" + filters.getAutoreNome() + "%");
            Predicate cognome = cb.like(subqueryAutore.get("cognome"), "%" + filters.getAutoreNome() + "%");

            // Select the Libro ID where one of their autore matches
            subquery.select(subqueryLibro.get("libroId")).where(
                    cb.or(nome, cognome));

            // Filter by Libro that match one of the Libro found in the subquery
            predicates.add(cb.in(root.get("libroId")).value(subquery));
        }
        if(filters.getCasaEditriceId() != null){
            predicates.add(cb.equal(root.get("casaDiProduzione").get("casaId"), filters.getCasaEditriceId()));
        }
        if(filters.getMinData() != null && filters.getMaxData() != null){
            predicates.add(cb.between(root.get("dataDiPubblicazione"), filters.getMinData(), filters.getMaxData()));
        }
        if(filters.getMinData() != null && filters.getMaxData() == null){
            predicates.add(cb.greaterThan(root.get("dataDiPubblicazione"), filters.getMinData()));
        }
        if(filters.getMinData() == null && filters.getMaxData() != null){
            predicates.add(cb.lessThan(root.get("dataDiPubblicazione"), filters.getMaxData()));
        }
        if(filters.getMinVoto() != null && filters.getMaxVoto() != null){
            predicates.add(cb.between(root.get("voto"), filters.getMinVoto(), filters.getMaxVoto()));
        }
        if(filters.getMinVoto() != null && filters.getMaxVoto() == null){
            predicates.add(cb.greaterThan(root.get("voto"), filters.getMinVoto()));
        }
        if(filters.getMinVoto() == null && filters.getMaxVoto() != null){
            predicates.add(cb.lessThan(root.get("voto"), filters.getMaxVoto()));
        }
        if(filters.getTags() != null){
            for(int tagId : filters.getTags()) {
                Subquery<Integer> subquery = query.subquery(Integer.class);
                Root<Libro> subqueryLibro = subquery.from(Libro.class);
                Join<Libro, Tag> subqueryTag = subqueryLibro.join("tagSet");

                // Select the Libro ID where one of their autore matches
                subquery.select(subqueryLibro.get("libroId")).where(
                        cb.equal(subqueryTag.get("tagId"), tagId));

                // Filter by Libro that match one of the Libro found in the subquery
                predicates.add(cb.in(root.get("libroId")).value(subquery));
            }
        }

        query.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(query).getResultList();
    }
}