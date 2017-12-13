package fr.eseo.dis.woodward.filmography.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by rwoodward on 22/11/17.
 */
@Dao
public interface NotationDao {

  @Insert
  public void insertNotations(Notation... notations);

  @Query("SELECT * FROM notations")
  public List<Notation> findAllNotations();

  @Query("SELECT * FROM notations WHERE id_film = :idFilm")
  public List<Notation> findNotationsForFilm(int idFilm);

  @Query("SELECT * FROM notations, internautes where id_film = :idFilm AND notations.email = internautes.email ")
  public List<NotationWithInternaute> findNotationsWithInternautesForFilm(int idFilm);
}
