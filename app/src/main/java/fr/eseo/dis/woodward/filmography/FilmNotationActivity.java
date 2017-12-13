package fr.eseo.dis.woodward.filmography;

import static fr.eseo.dis.woodward.filmography.FilmographyActivity.FILM_EXTRA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import fr.eseo.dis.woodward.filmography.data.DummyData;
import fr.eseo.dis.woodward.filmography.data.Film;
import fr.eseo.dis.woodward.filmography.data.FilmographyDatabase;
import fr.eseo.dis.woodward.filmography.data.Internaute;
import fr.eseo.dis.woodward.filmography.data.Notation;
import fr.eseo.dis.woodward.filmography.data.NotationWithInternaute;
import java.util.ArrayList;
import java.util.List;

public class FilmNotationActivity extends AppCompatActivity {

  private FilmNotationAdapter filmNotationAdapter;
  private int idFilm;
  private Film film;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_film_notation);
    Intent intent = getIntent();
    Bundle data = getIntent().getExtras();
    film = (Film) data.getParcelable(FILM_EXTRA);
    idFilm = film.getIdFilm();
    RecyclerView recycler = (RecyclerView) findViewById(R.id.notationList);
    recycler.setHasFixedSize(true);
    LinearLayoutManager llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recycler.setLayoutManager(llm);
    filmNotationAdapter = new FilmNotationAdapter(this);
    recycler.setAdapter(filmNotationAdapter);
    loadNotationDetails();
  }

  private void loadNotationDetails() {

    Log.d("FilmNotationActivity", "Find Notation Information for film: " + film.getTitre());

    /*List<NotationWithInternaute> noteInter = new ArrayList<>();
    for (Notation notation : DummyData.getNotations()) {
      if (notation.getId_film() == idFilm) {
        int indice = 0;
        Internaute internaute = null;

        Log.d("FilmDetailsActivity", "Find Internatue for note for film: " + film.getTitre());

        while (indice < DummyData.getInternaute().size() && internaute == null) {
          if (DummyData.getInternaute().get(indice).getEmail().equals(notation.getEmail())) {
            internaute = DummyData.getInternaute().get(indice);
            noteInter.add(new NotationWithInternaute(notation, internaute));
          } else {
            indice++;
          }
        }
      }
    }
    filmNotationAdapter.setNotations(noteInter);
    */
    filmNotationAdapter.setNotations(FilmographyDatabase.getDatabase(this).notationDao().findNotationsWithInternautesForFilm(idFilm));
    filmNotationAdapter.notifyDataSetChanged();
  }

}
