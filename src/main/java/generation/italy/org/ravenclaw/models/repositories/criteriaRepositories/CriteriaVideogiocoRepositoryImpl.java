package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.entities.Tag;
import generation.italy.org.ravenclaw.models.entities.Videogioco;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import generation.italy.org.ravenclaw.models.searchCriteria.VideogiocoFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
        Predicate[] predicates = buildPredicate(cb, root, filters);
        query.where(predicates);
        query.distinct(true);
        if(filters.isOrderByVoto()){
            query.orderBy(cb.desc(root.get("voto")));
        }
        List<Videogioco> videogiochi = em.createQuery(query).setFirstResult(filters.getPageSize()*filters.getNumPage()).setMaxResults(filters.getPageSize())
                .getResultList();

        CriteriaQuery<Long> totalQuery = cb.createQuery(Long.class);
        totalQuery.select(cb.count(totalQuery.from(Videogioco.class)));
        totalQuery.where(predicates);

        Long totaleVideogiochi = em.createQuery(totalQuery).getSingleResult();
        return new PageImpl<>(videogiochi, PageRequest.of(filters.getNumPage(), filters.getPageSize()), totaleVideogiochi);
    }

    private Predicate[] buildPredicate(CriteriaBuilder cb, Root<Videogioco> root, VideogiocoFilterCriteria filters){
        List<Predicate> predicates = new ArrayList<>();

        if(filters.getTitolo() != null){
            Expression<String> lowerTitolo = cb.lower(root.get("titolo"));
            predicates.add(cb.like(lowerTitolo, "%" + filters.getTitolo().toLowerCase() + "%"));
        }

        if(filters.getNomeCasaDiProduzione() != null && !filters.getNomeCasaDiProduzione().isEmpty()){
            Join<Videogioco, Casa> casaProdJoin = root.join("casaDiProduzione", JoinType.INNER);
            predicates.add(cb.like(cb.lower(casaProdJoin.get("nome")), "%" + filters.getNomeCasaDiProduzione() + "%"));
        }

        if(filters.getNomeCasaDiPubblicazione() != null && !filters.getNomeCasaDiPubblicazione().isEmpty()) {
            Join<Videogioco, Casa> casaPubJoin = root.join("casaDiPubblicazione", JoinType.INNER);
            predicates.add(cb.like(cb.lower(casaPubJoin.get("nome")), "%" + filters.getNomeCasaDiPubblicazione() + "%"));
        }

        if(filters.getMinDataDiPubblicazione() != null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("dataDiPubblicazione"), filters.getMinDataDiPubblicazione()));
        }

        if(filters.getMaxDataDiPubblicazione() != null){
            predicates.add(cb.lessThanOrEqualTo(root.get("dataDiPubblicazione"), filters.getMaxDataDiPubblicazione()));

        }

        if(filters.getMinOreDiGiocoStoriaPrincipale() != null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("oreStoriaPrincipale"), filters.getMinOreDiGiocoStoriaPrincipale()));
        }

        if(filters.getMaxOreDiGiocoStoriaPrincipale() != null){
            predicates.add(cb.lessThanOrEqualTo(root.get("oreStoriaPrincipale"), filters.getMaxOreDiGiocoStoriaPrincipale()));
        }


        if(filters.getMinVoto() != null){
            predicates.add(cb.lessThanOrEqualTo(root.get("voto"), filters.getMinVoto()));
        }

        if(filters.getMaxVoto() != null){
            predicates.add(cb.lessThanOrEqualTo(root.get("voto"), filters.getMaxVoto()));
        }

        if(filters.getTags() != null && !filters.getTags().isEmpty()){
            Join<Videogioco, Tag> tagJoin = root.join("tags", JoinType.INNER);
            predicates.add(tagJoin.get("id").in(filters.getTags()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
