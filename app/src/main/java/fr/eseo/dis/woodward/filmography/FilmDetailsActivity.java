package fr.eseo.dis.woodward.filmography;

import static fr.eseo.dis.woodward.filmography.FilmographyActivity.FILM_EXTRA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fr.eseo.dis.woodward.filmography.data.Artiste;
import fr.eseo.dis.woodward.filmography.data.DummyData;
import fr.eseo.dis.woodward.filmography.data.Film;
import fr.eseo.dis.woodward.filmography.data.FilmographyDatabase;
import fr.eseo.dis.woodward.filmography.data.Notation;
import fr.eseo.dis.woodward.filmography.data.Pays;
import fr.eseo.dis.woodward.filmography.data.Role;
import fr.eseo.dis.woodward.filmography.data.RoleWithActor;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FilmDetailsActivity extends AppCompatActivity {

  private TextView titre;
  private TextView genre;
  private TextView annee;
  private TextView realisateur;
  private FilmRoleAdapter aa;
  private Film film;
  private TextView resume;
  private TextView note;
  private Button btnDetails;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_film_details);
    int clickedFilm = 0;
    Intent intent = getIntent();
    Bundle data = getIntent().getExtras();
    film = (Film) data.getParcelable(FILM_EXTRA);
    titre = (TextView) findViewById(R.id.tv_details_title);
    genre = (TextView) findViewById(R.id.tv_details_genre);
    annee = (TextView) findViewById(R.id.tv_details_annee);
    resume = (TextView) findViewById(R.id.tv_details_resume);
    realisateur = (TextView) findViewById(R.id.tv_details_realisateur);
    note = (TextView) findViewById(R.id.tv_details_note);
    btnDetails = (Button) findViewById(R.id.button_details);
    titre.setText(film.getTitre());
    genre.setText(film.getGenre());
    resume.setText(film.getResume());
    btnDetails.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(FilmDetailsActivity.this, FilmNotationActivity.class);
        intent.putExtra(FILM_EXTRA, film);
        startActivity(intent);
      }
    });

    RecyclerView recList = (RecyclerView) findViewById(R.id.film_details_acteurs);
    recList.setHasFixedSize(true);
    LinearLayoutManager llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recList.setLayoutManager(llm);
    aa = new FilmRoleAdapter(this, clickedFilm);
    recList.setAdapter(aa);
    loadFilmDetails();
  }

  private void loadFilmDetails() {
    /*
    Pays pays = null;
    Artiste reali = null;
    int minNote = 0;
    int maxNote = 0;
    float avgNote = 0;
    int nbNotes = 0;

    Log.d("FilmDetailsActivity", "Find Country Information for film: " + film.getTitre());

    int indice = 0;
    while (indice < DummyData.getPays().size() && pays == null) {
      if (DummyData.getPays().get(indice).getCode().equals(film.getCodePays())) {
        pays = DummyData.getPays().get(indice);
      } else {
        indice++;
      }
    }

    Log.d("FilmDetailsActivity", "Find Director Information for film: " + film.getTitre());

    indice = 0;
    while (indice < DummyData.getArtistes().size() && reali == null) {
      if (DummyData.getArtistes().get(indice).getIdArtiste() == (film.getIdRealisateur())) {
        reali = DummyData.getArtistes().get(indice);
      } else {
        indice++;
      }
    }

    Log.d("FilmDetailsActivity", "Find Roles Information for film: " + film.getTitre());

    List<RoleWithActor> actorRole = new ArrayList<>();
    for (Role role : DummyData.getRoles()) {
      if (role.getIdFilm() == film.getIdFilm()) {
        Artiste artiste = null;
        indice = 0;

        Log.d("FilmDetailsActivity",
            "Find Actor Information for role: " + role.getNomRole() + " in film: " + film
                .getTitre());

        while (indice < DummyData.getArtistes().size() && artiste == null) {
          if (DummyData.getArtistes().get(indice).getIdArtiste() == role.getIdActeur()) {
            artiste = DummyData.getArtistes().get(indice);
            actorRole.add(new RoleWithActor(artiste, role));

          } else {
            indice++;
          }
        }
      }
    }
    aa.setRoles(actorRole);

    Log.d("FilmDetailsActivity", "Find Notation Information for film: " + film.getTitre());

    List<Notation> notations = new ArrayList<>();
    for (Notation notation : DummyData.getNotations()) {
      if (notation.getId_film() == film.getIdFilm()) {
        notations.add(notation);
      }
    }
    if (notations == null || notations.size() == 0) {
      nbNotes = 0;
      avgNote = 0;

    } else {
      avgNote = minNote;
      nbNotes = notations.size();
      for (Notation notation : notations) {
        avgNote = avgNote + notation.getNote();
      }
      avgNote = (float) avgNote / (float) nbNotes;
    }

    Log.d("FilmDetailsActivity", "Populating View for film: " + film.getTitre());

    annee.setText(String.valueOf(film.getAnnee()) + " (" + pays.getNom() + ")");
    realisateur.setText(getString(R.string.realisateur_label) + reali.getPrenom() + " " + reali.getNom());
    DecimalFormat df = new DecimalFormat("0.0");
    note.setText(df.format(avgNote) + getString(R.string.average_note_from) + nbNotes + getString(R.string.note_nbr_users));
    aa.notifyDataSetChanged();
    if (avgNote == 0) {
      btnDetails.setVisibility(View.GONE);
    } else {
      btnDetails.setVisibility(View.VISIBLE);
    }*/
    float avgNote;
    int nbNotes;

    Pays pays = FilmographyDatabase.getDatabase(this).paysDao().findPaysFromCode(film.getCodePays());
    Artiste reali = FilmographyDatabase.getDatabase(this).artisteDao().findArtisteFromId(film.getIdRealisateur());
    List<RoleWithActor>roles = FilmographyDatabase.getDatabase(this).roleDao().findRolesWithActeursForFilm(film.getIdFilm());
    List<Notation> notations = FilmographyDatabase.getDatabase(this).notationDao()
        .findNotationsForFilm(film.getIdFilm());
    if(notations == null || notations.size()==0){
      avgNote = 0;
      nbNotes = 0;
    }
    else{
      avgNote = 0;
      nbNotes = notations.size();
      for(Notation notation : notations){
        avgNote += notation.getNote();
      }
      avgNote = (float)avgNote/(float)nbNotes;
    }
    annee.setText(String.valueOf(film.getAnnee()) + " (" + pays.getNom() + ")");
    realisateur.setText(getString(R.string.realisateur_label) + reali.getPrenom() + " " + reali.getNom());
    DecimalFormat df = new DecimalFormat("0.0");
    note.setText(df.format(avgNote) + getString(R.string.average_note_from) + nbNotes + getString(R.string.note_nbr_users));
    aa.setRoles(roles);
    aa.notifyDataSetChanged();
    if (avgNote == 0) {
      btnDetails.setVisibility(View.GONE);
    } else {
      btnDetails.setVisibility(View.VISIBLE);
    }
  }
}
