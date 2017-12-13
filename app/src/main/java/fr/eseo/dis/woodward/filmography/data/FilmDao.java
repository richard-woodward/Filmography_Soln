package fr.eseo.dis.woodward.filmography.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by rwoodward on 22/11/17.
 */
@Dao
public interface FilmDao {

  @Query("SELECT * FROM films")
  public List<Film> findAllFilms();

  @Query("SELECT * FROM films WHERE id_film = :idFilm")
  public Film findFilmFromId(int idFilm);

}
