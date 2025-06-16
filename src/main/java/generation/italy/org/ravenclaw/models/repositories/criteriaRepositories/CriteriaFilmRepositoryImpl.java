package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaFilmRepositoryImpl implements CriteriaFilmRepository {
    private EntityManager em;

    @Autowired
    public CriteriaFilmRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Page<Film> searchFilmByFilters(FilmFilterCriteria filmFilters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Film> query = cb.createQuery(Film.class);
        Root<Film> rootFilm = query.from(Film.class);
        Join<Film, Tag> tagJoin = null;
        if (filmFilters.getTags() != null && !filmFilters.getTags().isEmpty()) {
            tagJoin = rootFilm.join("tagSet");
        }
        Predicate[] predicates = buildPredicates(cb, rootFilm, filmFilters, tagJoin);
        query.where(predicates);
        if (tagJoin != null) {
            query.groupBy(rootFilm.get("id"));
            query.having(
                    cb.equal(
                            cb.countDistinct(tagJoin.get("id")),
                            filmFilters.getTags().size()
                    )
            );
        }
        query.distinct(true);

        //METODI DI SORTING
        String sortOrder = filmFilters.getSort();
        if (sortOrder.equalsIgnoreCase("orderByVotoDesc")) {
            query.orderBy(cb.desc(rootFilm.get("voto")));
        } else if (sortOrder.equalsIgnoreCase("orderByVotoAsc")) {
            query.orderBy(cb.asc(rootFilm.get("voto")));
        } else if (sortOrder.equalsIgnoreCase("orderByTitoloDesc")) {
            query.orderBy(cb.desc(rootFilm.get("titolo")));
        } else if (sortOrder.equalsIgnoreCase("orderByTitoloAsc")) {
            query.orderBy(cb.asc(rootFilm.get("titolo")));
        } else if (sortOrder.equalsIgnoreCase("orderByDataPubblicazioneDesc")) {
            query.orderBy(cb.desc(rootFilm.get("dataDiPubblicazione")));
        } else if (sortOrder.equalsIgnoreCase("orderByDataDiPubblicazioneAsc")) {
            query.orderBy(cb.asc(rootFilm.get("dataDiPubblicazione")));
            //SORTING SPECIFICI PER FILM DA QUI IN POI
        } else if (sortOrder.equalsIgnoreCase("orderByDurataFilmDesc")) {
            query.orderBy(cb.desc(rootFilm.get("durata")));
        } else if (sortOrder.equalsIgnoreCase("orderByDurataFilmAsc")) {
            query.orderBy(cb.asc(rootFilm.get("durata")));
        }

        List<Film> films = em.createQuery(query).setFirstResult(filmFilters.getPageSize()*filmFilters.getNumPage()).setMaxResults(filmFilters.getPageSize())
                .getResultList();
    //conteggio totale
        CriteriaQuery<Long> totalQuery = cb.createQuery(Long.class);
        Root<Film> totalRoot = totalQuery.from(Film.class);
        Join<Film, Tag> totalTagJoin = null;
        if (filmFilters.getTags() != null && !filmFilters.getTags().isEmpty()) {
            totalTagJoin = totalRoot.join("tagSet");
        }
        Predicate[] countPredicates = buildPredicates(cb, totalRoot, filmFilters, totalTagJoin);
        totalQuery.select(cb.countDistinct(totalQuery.from(Libro.class)));
        totalQuery.where(countPredicates);
        if (totalTagJoin != null) {
            totalQuery.groupBy(totalRoot.get("id"));
            totalQuery.having(
                    cb.equal(
                            cb.countDistinct(totalTagJoin.get("id")),
                            filmFilters.getTags().size()
                    )
            );
        }
        Long totaleFilm = em.createQuery(totalQuery).getSingleResult();
        return new PageImpl<>(films, PageRequest.of(filmFilters.getNumPage(), filmFilters.getPageSize()), totaleFilm);
    }

    private Predicate[] buildPredicates(CriteriaBuilder cb, Root<Film> rootFilm, FilmFilterCriteria filmFilters, Join<Film,Tag> tagJoin){
        List<Predicate> predicates = new ArrayList<>();

        //=== FILM ========================

        if (filmFilters.getTitolo() != null) {
            predicates.add(cb.like(cb.lower(rootFilm.get("titolo")), "%" + filmFilters.getTitolo().toLowerCase() + "%"));
        }
        if (filmFilters.getCasaDiProduzioneNome() != null) {
            predicates.add(cb.like(rootFilm.get("casaDiProduzioneNome"), "%" + filmFilters.getCasaDiProduzioneNome() + "%"));
        }
        if (filmFilters.getCasaDiPubblicazioneNome() != null) {
            predicates.add(cb.like(rootFilm.get("casaDiPubblicazioneNome"), "%" + filmFilters.getCasaDiPubblicazioneNome() + "%"));
        }
        //date
        if (filmFilters.getMinData() != null && filmFilters.getMaxData() != null) {
            predicates.add(cb.between(rootFilm.get("dataDiPubblicazione"),  filmFilters.getMinData(), filmFilters.getMaxData()));
        }
        if (filmFilters.getMinData() != null && filmFilters.getMaxData() == null) {
            predicates.add(cb.greaterThanOrEqualTo(rootFilm.get("dataDiPubblicazione"), filmFilters.getMinData()));
        }
        if (filmFilters.getMinData() == null && filmFilters.getMaxData() != null) {
            predicates.add(cb.greaterThanOrEqualTo(rootFilm.get("dataDiPubblicazione"), filmFilters.getMaxData()));
        }
        if (filmFilters.getDataDiPubblicazione() != null) {
            predicates.add(cb.equal(rootFilm.get("dataDiPubblicazione"), filmFilters.getDataDiPubblicazione()));
        }
        if (filmFilters.getMinVoto() != null && filmFilters.getMaxVoto() != null) {
            predicates.add(cb.between(rootFilm.get("voto"), filmFilters.getMinVoto(), filmFilters.getMaxVoto()));
        }
        if (filmFilters.getMinVoto() != null && filmFilters.getMaxVoto() == null) {
            predicates.add(cb.greaterThanOrEqualTo(rootFilm.get("voto"), filmFilters.getMinVoto()));
        }
        if (filmFilters.getMinVoto() == null && filmFilters.getMaxVoto() != null) {
            predicates.add(cb.lessThanOrEqualTo(rootFilm.get("voto"), filmFilters.getMaxVoto()));
        }


        //=== AUTORE ===============================

        //per far arrivare dati dalla tabella autore

        if (filmFilters.getAutoreNome() != null) {
            Join<Film, CrewFilm> crewJoin = rootFilm.join("crew");
            Join<Join<Film, CrewFilm>, Autore> autoreJoin = crewJoin.join("autore");

            String[] keywords = filmFilters.getAutoreNome().trim().toLowerCase().split("\\s+");

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

            List<Predicate> keywordPredicates = new ArrayList<>();
            for (String kw : keywords) {
                keywordPredicates.add(cb.like(fullName, "%" + kw + "%"));
            }

            // Unisci i predicati con OR (almeno una keyword deve essere contenuta)
            Predicate autoreKeywordMatch = cb.or(keywordPredicates.toArray(new Predicate[0]));

            predicates.add(autoreKeywordMatch);
        }

        //=== TAGS ===
        if(filmFilters.getTags() != null){
            predicates.add(tagJoin.get("tagId").in(filmFilters.getTags()));

        }
        return predicates.toArray(new Predicate[0]);
    }
}
