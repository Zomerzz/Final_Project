package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.*;
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
            Expression<String> lowerTitolo = cb.lower(root.get("titolo"));
            predicates.add(cb.like(lowerTitolo, "%" + filters.getTitolo().toLowerCase() + "%"));
        }
        if(filters.getNumeroPagine() != null){
            predicates.add(cb.lessThanOrEqualTo(root.get("numeroPagine"), filters.getNumeroPagine()));
        }

        if (filters.getAutoreNome() != null) {
            // Join tra Libro e Autore
            Join<Libro, Autore> autoreJoin = root.join("autoreSet");

            // Split delle parole chiave inserite nel campo nome
            String[] keywords = filters.getAutoreNome().trim().toLowerCase().split("\\s+");

            // Coalesce per evitare null su nome, secondoNome e cognome
            Expression<String> nome = cb.coalesce(cb.lower(autoreJoin.get("nome")), "");
            Expression<String> secondoNome = cb.coalesce(cb.lower(autoreJoin.get("secondoNome")), "");
            Expression<String> cognome = cb.coalesce(cb.lower(autoreJoin.get("cognome")), "");

            // Concatenazione per costruire fullName = nome + " " + secondoNome + " " + cognome
            Expression<String> fullName = cb.concat(
                    cb.concat(
                            cb.concat(nome, cb.literal(" ")),
                            cb.concat(secondoNome, cb.literal(" "))
                    ),
                    cognome
            );

            // Lista di predicati LIKE per ciascuna keyword
            List<Predicate> keywordPredicates = new ArrayList<>();
            for (String kw : keywords) {
                keywordPredicates.add(cb.like(fullName, "%" + kw + "%"));
            }

            // Unisci i predicati con OR (almeno una keyword deve essere contenuta)
            Predicate autoreKeywordMatch = cb.or(keywordPredicates.toArray(new Predicate[0]));

            // Aggiungi il predicato alla lista della query principale
            predicates.add(autoreKeywordMatch);
        }


        if(filters.getCasaEditriceNome() != null){
            Join<Libro, Casa> casaJoin = root.join("casaEditrice", JoinType.INNER);
            //predicates.add(cb.like(cb.lower(casaJoin.get("listaLibriProduzione")), "%" + filters.getCasaEditriceNome() + "%"));
            predicates.add(cb.like(cb.lower(casaJoin.get("nome")), "%" + filters.getCasaEditriceNome().toLowerCase() + "%"));
        }
        if(filters.getMinData() != null && filters.getMaxData() != null){
            predicates.add(cb.between(root.get("dataDiPubblicazione"), filters.getMinData(), filters.getMaxData()));
        }
        if(filters.getMinData() != null && filters.getMaxData() == null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("dataDiPubblicazione"), filters.getMinData()));
        }
        if(filters.getMinData() == null && filters.getMaxData() != null){
            predicates.add(cb.lessThanOrEqualTo(root.get("dataDiPubblicazione"), filters.getMaxData()));
        }
        if(filters.getMinVoto() != null && filters.getMaxVoto() != null){
            predicates.add(cb.between(root.get("voto"), filters.getMinVoto(), filters.getMaxVoto()));
        }
        if(filters.getMinVoto() != null && filters.getMaxVoto() == null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("voto"), filters.getMinVoto()));
        }
        if(filters.getMinVoto() == null && filters.getMaxVoto() != null){
            predicates.add(cb.lessThanOrEqualTo(root.get("voto"), filters.getMaxVoto()));
        }
        if(filters.getTags() != null){
            for(int tagId : filters.getTags()) {
                Subquery<Integer> subquery = query.subquery(Integer.class);
                Root<Libro> subqueryLibro = subquery.from(Libro.class);
                Join<Libro, Tag> subqueryTag = subqueryLibro.join("tagSet");

                subquery.select(subqueryLibro.get("libroId")).where(
                        cb.equal(subqueryTag.get("tagId"), tagId));

                predicates.add(cb.in(root.get("libroId")).value(subquery));
            }
        }

        query.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(query).getResultList();
    }
}