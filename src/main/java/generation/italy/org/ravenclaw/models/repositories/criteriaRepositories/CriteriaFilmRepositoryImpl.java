package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        Predicate[] predicates = buildPredicates(cb, rootFilm, filmFilters);
        query.where(predicates);
        query.distinct(true);

        CriteriaQuery<Long> totalQuery = cb.createQuery(Long.class);
        totalQuery.select(cb.count(totalQuery.from(Film.class)));
        totalQuery.where(predicates);

        int totaleFilm = Math.toIntExact(em.createQuery(totalQuery).getSingleResult());
        //TODO CREARE IL PAGE E FARLO TORNARE
        return null;
    }

    private Predicate[] buildPredicates(CriteriaBuilder cb, Root<Film> rootFilm, FilmFilterCriteria filmFilters){
        List<Predicate> predicates = new ArrayList<>();

        //=== FILM ========================

        if (filmFilters.getTitolo() != null) {
            predicates.add(cb.like(rootFilm.get("titolo"), "%" + filmFilters.getTitolo() + "%"));
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
            // Join tra Film e Autore
            Join<Film, CrewFilm> crewJoin = rootFilm.join("crew");
            Join<Join<Film, CrewFilm>, Autore> autoreJoin = crewJoin.join("autore");

            // Split delle parole chiave inserite nel campo nome
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

        //=== TAGS ===
        if(filmFilters.getTags() != null){
            // Join diretta tra Libro e Tag
            Join<Film, Tag> tagJoin = rootFilm.join("tagSet", JoinType.INNER);
            // Predicate: il tagId è uno di quelli passati nella lista
            predicates.add(tagJoin.get("tagId").in(filmFilters.getTags()));

        }
        return predicates.toArray(new Predicate[0]);
    }
}
