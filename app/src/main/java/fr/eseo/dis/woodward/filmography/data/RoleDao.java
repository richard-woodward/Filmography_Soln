package fr.eseo.dis.woodward.filmography.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by rwoodward on 22/11/17.
 */
@Dao
public interface RoleDao {

  @Insert
  public void insertRoles(Role... roles);

  @Query("SELECT * FROM roles")
  public List<Role> findAllRoles();

  @Query("SELECT * FROM roles WHERE id_film = :idFilm")
  public List<Role> findRolesForFilm(int idFilm);

  @Query("SELECT * FROM roles, artistes WHERE id_film = :idFilm AND roles.id_acteur = artistes.id_artiste")
  public List<RoleWithActor>findRolesWithActeursForFilm(int idFilm);

}
