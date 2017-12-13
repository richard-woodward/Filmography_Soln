package fr.eseo.dis.woodward.filmography.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import java.util.List;

/**
 * Created by rwoodward on 20/11/17.
 */

public class RoleWithActor {
  @Relation(parentColumn = "id_acteur",entity=Artiste.class,entityColumn = "id_artiste")
  public List<Artiste> actors;
  @Embedded
  public Role role;


}
