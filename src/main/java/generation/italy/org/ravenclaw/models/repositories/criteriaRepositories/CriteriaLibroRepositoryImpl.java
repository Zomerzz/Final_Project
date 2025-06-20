package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    public Page<Libro> searchLibroByFilters(LibroFilterCriteria filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Libro> query = cb.createQuery(Libro.class);
        Root<Libro> root = query.from(Libro.class);

        Join<Libro, Tag> tagJoin = null;
        if (filters.getTags() != null && !filters.getTags().isEmpty()) {
            tagJoin = root.join("tagSet");
        }

        Predicate[] predicates = buildPredicates(cb,root,filters,tagJoin);

        query.where(predicates);

        if (tagJoin != null) {
            query.groupBy(root.get("id"));
            query.having(
                    cb.equal(
                            cb.countDistinct(tagJoin.get("id")),
                            filters.getTags().size()
                    )
            );
        }

        query.distinct(true);
        //METODI DI SORTING
        String sortOrder = filters.getSort();
        if (sortOrder.equalsIgnoreCase("orderByVotoDesc")) {
            query.orderBy(cb.desc(root.get("voto")));
        } else if (sortOrder.equalsIgnoreCase("orderByVotoAsc")) {
            query.orderBy(cb.asc(root.get("voto")));
        } else if (sortOrder.equalsIgnoreCase("orderByTitoloDesc")) {
            query.orderBy(cb.desc(root.get("titolo")));
        } else if (sortOrder.equalsIgnoreCase("orderByTitoloAsc")) {
            query.orderBy(cb.asc(root.get("titolo")));
        } else if (sortOrder.equalsIgnoreCase("orderByDataPubblicazioneDesc")) {
            query.orderBy(cb.desc(root.get("dataDiPubblicazione")));
        } else if (sortOrder.equalsIgnoreCase("orderByDataDiPubblicazioneAsc")) {
            query.orderBy(cb.asc(root.get("dataDiPubblicazione")));
            //SORTING SPECIFICI PER FILM DA QUI IN POI
        } else if (sortOrder.equalsIgnoreCase("orderByNumeroDiPagineDesc")) {
            query.orderBy(cb.desc(root.get("numeroPagine")));
        } else if (sortOrder.equalsIgnoreCase("orderByNumeroDiPagineAsc")) {
            query.orderBy(cb.asc(root.get("numeroPagine")));
        }




        List<Libro> libri = em.createQuery(query).setFirstResult(filters.getPageSize()*filters.getNumPage()).setMaxResults(filters.getPageSize())
                .getResultList();

        // Conteggio totale

        CriteriaQuery<Long> totalQuery = cb.createQuery(Long.class);
        Root<Libro> totalRoot = totalQuery.from(Libro.class);
        Join<Libro, Tag> totalTagJoin = null;
        if (filters.getTags() != null && !filters.getTags().isEmpty()) {
            totalTagJoin = totalRoot.join("tagSet");
        }

        Predicate[] countPredicates = buildPredicates(cb, totalRoot, filters, totalTagJoin);
        totalQuery.select(cb.countDistinct(totalQuery.from(Libro.class)));
        totalQuery.where(countPredicates);
        if (totalTagJoin != null) {
            totalQuery.groupBy(totalRoot.get("id"));
            totalQuery.having(
                    cb.equal(
                            cb.countDistinct(totalTagJoin.get("id")),
                            filters.getTags().size()
                    )
            );
        }
        long totaleLibri = em.createQuery(totalQuery).getSingleResult();

        return new PageImpl<>(libri, PageRequest.of(filters.getNumPage(), filters.getPageSize()), totaleLibri);
    }

    private Predicate[] buildPredicates(CriteriaBuilder cb, Root<Libro> root, LibroFilterCriteria filters, Join<Libro, Tag> tagJoin){
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
            predicates.add(tagJoin.get("tagId").in(filters.getTags()));

        }
        return predicates.toArray(new Predicate[0]);
    }
}