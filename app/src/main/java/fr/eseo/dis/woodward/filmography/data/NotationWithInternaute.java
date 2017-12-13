package fr.eseo.dis.woodward.filmography.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import java.util.List;

/**
 * Created by rwoodward on 20/11/17.
 */

public class NotationWithInternaute {
  @Embedded
  public Notation notation;
  @Relation(parentColumn = "email", entityColumn = "email", entity=Internaute.class)
  public List<Internaute> internautes;



}
