package fr.eseo.dis.woodward.filmography.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by rwoodward on 22/11/17.
 */
@Database(entities = {Internaute.class, Pays.class, Artiste.class, Film.class, Notation.class, Role.class},
          version = 2
)
public abstract class FilmographyDatabase extends RoomDatabase{

  private static FilmographyDatabase INSTANCE;

  public abstract InternauteDao internauteDao();

  public abstract PaysDao paysDao();

  public abstract ArtisteDao artisteDao();

  public abstract FilmDao filmDao();

  public abstract NotationDao notationDao();

  public abstract RoleDao roleDao();

  public static FilmographyDatabase getDatabase(Context context){
    if(INSTANCE == null){
      //Database needs to be 'bound' to a context, identified by a sub class of RoomDatabase
      // and have a filename where the database will be stored physically on the device
      INSTANCE = Room.databaseBuilder(context, FilmographyDatabase.class, "filmography.db")
          // For ease of use only => Need to delete this for production code
          .allowMainThreadQueries()
          .addCallback(new FilmographyDatabaseCallback())
          //When migrating delete the database and recreate it
          .fallbackToDestructiveMigration()
          //Create the database
          .build();
    }
    return INSTANCE;
  }

  public static void destroyInstance(){
    INSTANCE = null;
  }
}
