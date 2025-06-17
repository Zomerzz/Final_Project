package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository  extends JpaRepository<Tag, Integer> {
    @Query("SELECT tag.tagId FROM LibroLetto ll JOIN ll.libro l JOIN l.tagSet tag WHERE ll.utente.id = :utenteId" +
            " AND tag.isGenere = false GROUP BY tag ORDER BY COUNT(l) DESC")
    List<Integer> findFavouriteLibroTagsByUtenteId(@Param("utenteId") int utenteId);
    @Query("SELECT tag.tagId FROM LibroLetto ll JOIN ll.libro l JOIN l.tagSet tag WHERE ll.utente.id = :utenteId" +
            " AND tag.isGenere = true GROUP BY tag ORDER BY COUNT(l) DESC")
    List<Integer> findFavouriteLibroGenresByUtenteId(int utenteId);


    @Query("SELECT tag.tagId FROM FilmVisto fv JOIN fv.film f JOIN f.tagSet tag WHERE fv.utente.id = :utenteId" +
            " AND tag.isGenere = false GROUP BY tag ORDER BY COUNT(l) DESC")
    List<Integer> findFavouriteFilmTagsByUtenteId(@Param("utenteId") int utenteId);
    @Query("SELECT tag.tagId FROM FilmVisto fv JOIN fv.film f JOIN f.tagSet tag WHERE fv.utente.id = :utenteId" +
            " AND tag.isGenere = true GROUP BY tag ORDER BY COUNT(l) DESC")
    List<Integer> findFavouriteFilmGenresByUtenteId(int utenteId);


    @Query("SELECT tag.tagId FROM VideogiocoGiocato vg JOIN vg.videogioco v JOIN v.tagSet tag WHERE vg.utente.id = :utenteId" +
            " AND tag.isGenere = false GROUP BY tag ORDER BY COUNT(l) DESC")
    List<Integer> findFavouriteVideogiocoTagsByUtenteId(@Param("utenteId") int utenteId);
    @Query("SELECT tag.tagId FROM VideogiocoGiocato vg JOIN vg.videogioco v JOIN v.tagSet tag WHERE vg.utente.id = :utenteId" +
            " AND tag.isGenere = true GROUP BY tag ORDER BY COUNT(l) DESC")
    List<Integer> findFavouriteVideogiocoGenresByUtenteId(int utenteId);
}
