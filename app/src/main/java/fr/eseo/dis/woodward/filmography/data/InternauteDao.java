package fr.eseo.dis.woodward.filmography.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by rwoodward on 22/11/17.
 */
@Dao
public interface InternauteDao {

  @Query("SELECT * FROM internautes")
  public List<Internaute> findAllInternautes();
}
