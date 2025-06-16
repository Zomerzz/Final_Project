package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Tag;
import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;
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
public class CriteriaVideogiocoRepositoryImpl implements CriteriaVideogiocoRepository {
    private EntityManager em;

    @Autowired
    public CriteriaVideogiocoRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Page<Videogioco> searchVideogiocoByFilter(VideogiocoFilterCriteria filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Videogioco> query = cb.createQuery(Videogioco.class);
        Root<Videogioco> root = query.from(Videogioco.class);

        Join<Videogioco, Tag> tagJoin = null;
        if (filters.getTags() != null && !filters.getTags().isEmpty()) {
            tagJoin = root.join("tagSet");
        }

        Predicate[] predicates = buildPredicates(cb, root, tagJoin, filters);
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
        //Ordinamento
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
        }

        List<Videogioco> videogiochi = em.createQuery(query)
                .setFirstResult(filters.getPageSize() * filters.getNumPage())
                .setMaxResults(filters.getPageSize())
                .getResultList();

        // Conteggio totale
        CriteriaQuery<Long> totalQuery = cb.createQuery(Long.class);
        Root<Videogioco> totalRoot = totalQuery.from(Videogioco.class);
        Join<Videogioco, Tag> totalTagJoin = null;
        if (filters.getTags() != null && !filters.getTags().isEmpty()) {
            totalTagJoin = totalRoot.join("tagSet");
        }

        Predicate[] countPredicates = buildPredicates(cb, totalRoot, totalTagJoin, filters);
        totalQuery.select(cb.countDistinct(totalRoot));
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

        Long totaleVideogiochi = em.createQuery(totalQuery).getSingleResult();
        return new PageImpl<>(videogiochi, PageRequest.of(filters.getNumPage(), filters.getPageSize()), totaleVideogiochi);
    }

    private Predicate[] buildPredicates(CriteriaBuilder cb, Root<Videogioco> root, Join<Videogioco, Tag> tagJoin, VideogiocoFilterCriteria filters) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getTitolo() != null) {
            Expression<String> lowerTitolo = cb.lower(root.get("titolo"));
            predicates.add(cb.like(lowerTitolo, "%" + filters.getTitolo().toLowerCase() + "%"));
        }

        if (filters.getNomeCasaDiProduzione() != null && !filters.getNomeCasaDiProduzione().isEmpty()) {
            Join<Videogioco, Casa> casaProdJoin = root.join("casaDiProduzione", JoinType.INNER);
            predicates.add(cb.like(cb.lower(casaProdJoin.get("nome")), "%" + filters.getNomeCasaDiProduzione() + "%"));
        }

        if (filters.getNomeCasaDiPubblicazione() != null && !filters.getNomeCasaDiPubblicazione().isEmpty()) {
            Join<Videogioco, Casa> casaPubJoin = root.join("casaDiPubblicazione", JoinType.INNER);
            predicates.add(cb.like(cb.lower(casaPubJoin.get("nome")), "%" + filters.getNomeCasaDiPubblicazione() + "%"));
        }

        if (filters.getMinDataDiPubblicazione() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("dataDiPubblicazione"), filters.getMinDataDiPubblicazione()));
        }

        if (filters.getMaxDataDiPubblicazione() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("dataDiPubblicazione"), filters.getMaxDataDiPubblicazione()));
        }

        if (filters.getMinOreDiGiocoStoriaPrincipale() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("oreStoriaPrincipale"), filters.getMinOreDiGiocoStoriaPrincipale()));
        }

        if (filters.getMaxOreDiGiocoStoriaPrincipale() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("oreStoriaPrincipale"), filters.getMaxOreDiGiocoStoriaPrincipale()));
        }

        if (filters.getMinVoto() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("voto"), filters.getMinVoto()));
        }

        if (filters.getMaxVoto() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("voto"), filters.getMaxVoto()));
        }

        if (tagJoin != null) {
            predicates.add(tagJoin.get("id").in(filters.getTags()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
