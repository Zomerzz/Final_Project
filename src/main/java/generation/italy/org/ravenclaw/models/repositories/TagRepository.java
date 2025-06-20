package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository  extends JpaRepository<Tag, Integer> {
    @Query("SELECT tag.tagId FROM LibroLetto ll JOIN ll.libro l JOIN l.tagSet tag WHERE ll.utente.id = :utenteId" +
            " AND tag.isGenere = :isGenere GROUP BY tag ORDER BY COUNT(l) DESC")
    List<Integer> findFavouriteLibroTagsByUtenteId(@Param("utenteId") int utenteId, @Param("isGenere") boolean isGenere);


    @Query("SELECT tag.tagId FROM FilmVisto fv JOIN fv.film f JOIN f.tagSet tag WHERE fv.utente.id = :utenteId" +
            " AND tag.isGenere = :isGenere GROUP BY tag ORDER BY COUNT(f) DESC")
    List<Integer> findFavouriteFilmTagsByUtenteId(@Param("utenteId") int utenteId, @Param("isGenere") boolean isGenere);

    @Query("SELECT tag.tagId FROM VideogiocoGiocato vg JOIN vg.videogioco v JOIN v.tagSet tag WHERE vg.utente.id = :utenteId" +
            " AND tag.isGenere = :isGenere GROUP BY tag ORDER BY COUNT(v) DESC")
    List<Integer> findFavouriteVideogiocoTagsByUtenteId(@Param("utenteId") int utenteId, @Param("isGenere") boolean isGenere);

    List<Tag> findByIsGenere(boolean isGenere);

    @Query("SELECT tag FROM Tag tag JOIN tag.filmSet f WHERE f.filmId = :id")
    List<Tag> findByFilmId(@Param("id")int id);

    @Query("SELECT tag FROM Tag tag JOIN tag.libroSet f WHERE f.libroId = :id")
    List<Tag> findByLibroId(@Param("id")int id);

    @Query("SELECT tag FROM Tag tag JOIN tag.videogiocoSet f WHERE f.videogiocoId = :id")
    List<Tag> findByVideogiocoId(@Param("id")int id);
}
