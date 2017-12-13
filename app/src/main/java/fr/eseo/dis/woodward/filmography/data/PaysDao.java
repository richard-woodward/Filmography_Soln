package fr.eseo.dis.woodward.filmography.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by rwoodward on 22/11/17.
 */
@Dao
public interface PaysDao {

  @Insert
  public void insertPays(Pays... pays);

  @Query("SELECT * FROM pays")
  public List<Pays> findAllPays();

  @Query("SELECT * FROM pays WHERE code = :codePays")
  public Pays findPaysFromCode(String codePays);

}
