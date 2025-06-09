package generation.italy.org.ravenclaw.models.repositories.criteriaRepositories;

import generation.italy.org.ravenclaw.models.entities.Autore;
import generation.italy.org.ravenclaw.models.entities.Film;
import generation.italy.org.ravenclaw.models.searchCriteria.FilmFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Film> searchFilmByFilters(FilmFilterCriteria filmFilters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Film> query = cb.createQuery(Film.class);
        Root<Film> rootFilm = query.from(Film.class);
        List<Predicate> predicates = new ArrayList<>();

        //=== FILM ========================

        if (filmFilters.getTitolo() != null) {
            predicates.add(cb.like(rootFilm.get("titolo"), filmFilters.getTitolo()));
        }
        if (filmFilters.getCasaDiProduzioneNome() != null) {
            predicates.add(cb.like(rootFilm.get("casaDiProduzioneNome"), filmFilters.getCasaDiProduzioneNome()));
        }
        if (filmFilters.getCasaDiPubblicazioneNome() != null) {
            predicates.add(cb.like(rootFilm.get("casaDiPubblicazioneNome"), filmFilters.getCasaDiPubblicazioneNome()));
        }
        //date
        if (filmFilters.getMinData() != null && filmFilters.getMaxData() != null) {
            predicates.add(cb.between(rootFilm.get("dataDiPubblicazione"), filmFilters.getMinData(), filmFilters.getMaxData()));
        }
        if (filmFilters.getMinData() != null && filmFilters.getMaxData() == null) {
            predicates.add(cb.greaterThan(rootFilm.get("dataDiPubblicazione"), filmFilters.getMinData()));
        }
        if (filmFilters.getMinData() == null && filmFilters.getMaxData() != null) {
            predicates.add(cb.greaterThan(rootFilm.get("dataDiPubblicazione"), filmFilters.getMaxData()));
        }
        if (filmFilters.getDataDiPubblicazione() != null) {
            predicates.add(cb.greaterThan(rootFilm.get("dataDiPubblicazione"), filmFilters.getDataDiPubblicazione()));      //chiedere se va bene
        }
        if (filmFilters.getMinVoto() != null && filmFilters.getMaxVoto() != null) {
            predicates.add(cb.between(rootFilm.get("voto"), filmFilters.getMinVoto(), filmFilters.getMaxVoto()));
        }
        if (filmFilters.getMinVoto() != null && filmFilters.getMaxVoto() == null) {
            predicates.add(cb.greaterThan(rootFilm.get("voto"), filmFilters.getMinVoto()));
        }
        if (filmFilters.getMinVoto() == null && filmFilters.getMaxVoto() != null) {
            predicates.add(cb.lessThan(rootFilm.get("voto"), filmFilters.getMaxVoto()));
        }
        // Il voto lo metto in un if() solo per lui? O faccio come nella classe di Cami?  chiedere se va bene


        //=== AUTORE ===============================

        //per far arrivare dati dalla tabella autore
        CriteriaQuery<Autore> queryAutore = cb.createQuery(Autore.class);
        Root<Autore> rootAutore = query.from(Autore.class);

        if (filmFilters.getAutoreNome() != null) {                                                        //chiedere se va bene
            //array con le varie parole inserite nel campo nome dell'autore
            String[] keywords = filmFilters.getAutoreNome().
                    trim().
                    toLowerCase().
                    split("\\s+");

            // il coalesce ci salva dal fatto che i valori potrebbero essere null
            Expression<String> name = cb.coalesce(rootAutore.get("nome"), "");
            Expression<String> secondName = cb.coalesce(rootAutore.get("secondoNome"), "");
            Expression<String> lastName = cb.coalesce(rootAutore.get("cognome"), "");

            //la query concatenera i nomi, i secondi nomi e i cognomi
            Expression<String> fullName = cb.lower(
                    cb.concat(
                            cb.concat(
                                    cb.concat(name, cb.literal(" ")),
                                    secondName
                            ),
                            cb.concat(cb.literal(" "), lastName)
                    )
            );
            //controlla se ciascuna parola è inclusa nella concatenazione
            for (String kw : keywords) {
                predicates.add(cb.like(fullName, "%" + kw + "%"));
            }


        }
        query.where(predicates.toArray(new Predicate[0]));
        queryAutore.where(cb.equal(rootFilm.get("autoreNome"), rootAutore.get("autoreId")));
        return em.createQuery(query).getResultList();
    }
}
