package fr.eseo.dis.woodward.filmography.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by rwoodward on 22/11/17.
 */
@Dao
public interface ArtisteDao {

  @Query("SELECT * FROM artistes")
  public List<Artiste> findAllArtistes();

  @Query("SELECT * FROM artistes WHERE id_artiste = :idArtiste")
  public Artiste findArtisteFromId(int idArtiste);

}
