package fr.eseo.dis.woodward.filmography;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import fr.eseo.dis.woodward.filmography.data.DummyData;
import fr.eseo.dis.woodward.filmography.data.Film;
import fr.eseo.dis.woodward.filmography.data.FilmographyDatabase;

public class FilmographyActivity extends AppCompatActivity {

  public static final String FILM_EXTRA = "film_extra";

  public static int NEW_CARD_COUNTER;

  private FilmographyAdapter filmAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_filography);
    FilmographyActivity.NEW_CARD_COUNTER = 0;
    RecyclerView recycler = (RecyclerView) findViewById(R.id.cardList);
    recycler.setHasFixedSize(true);
    LinearLayoutManager llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recycler.setLayoutManager(llm);
    filmAdapter = new FilmographyAdapter(this);
    recycler.setAdapter(filmAdapter);
    loadAllFilmsData();

  }

  private void loadAllFilmsData() {

    //filmAdapter.setFilms(DummyData.getFilms());
    filmAdapter.setFilms(FilmographyDatabase.getDatabase(this).filmDao().findAllFilms());
    filmAdapter.notifyDataSetChanged();
  }


  public void clickItem(Film film) {
    Intent intent = new Intent(this, FilmDetailsActivity.class);
    intent.putExtra(FILM_EXTRA, film);
    startActivity(intent);
  }

}

