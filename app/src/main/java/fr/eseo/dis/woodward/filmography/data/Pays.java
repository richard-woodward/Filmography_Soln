package fr.eseo.dis.woodward.filmography.data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by rwoodward on 16/11/17.
 */
@Entity
public class Pays {

  @PrimaryKey
  @NonNull
  public String code;

  @NonNull
  public String nom;

  @NonNull
  public String langue;

  public Pays(@NonNull String code, @NonNull String nom, @NonNull String langue) {
    this.code = code;
    this.nom = nom;
    this.langue = langue;
  }
  @NonNull
  public String getCode() {
    return code;
  }

  public void setCode(@NonNull String code) {
    this.code = code;
  }

  @NonNull
  public String getNom() {
    return nom;
  }

  public void setNom(@NonNull String nom) {
    this.nom = nom;
  }

  @NonNull
  public String getLangue() {
    return langue;
  }

  public void setLangue(@NonNull String langue) {
    this.langue = langue;
  }
}
