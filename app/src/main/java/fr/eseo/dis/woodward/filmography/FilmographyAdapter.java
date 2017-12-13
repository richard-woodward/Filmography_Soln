package fr.eseo.dis.woodward.filmography;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.eseo.dis.woodward.filmography.data.Film;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rwoodward on 16/11/17.
 */

public class FilmographyAdapter extends
    RecyclerView.Adapter<FilmographyAdapter.FilmographyViewHolder> {

  private FilmographyActivity activity;
  private List<Film> films;
  private List<Integer> positionsExpanded;

  public FilmographyAdapter(FilmographyActivity filmographyActivity) {
    this.activity = filmographyActivity;
    setFilms(new ArrayList<Film>());
    positionsExpanded = new ArrayList<>();
  }

  public void setFilms(List<Film> films) {
    this.films = films;
    Log.d("setFilms()","Number films="+films.size());
  }

  @Override
  public int getItemCount() {
    return films.size();
  }

  @Override
  public FilmographyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View filmView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_film_card, parent, false);
    Log.d("FilmographyAdaper","onCreateViewHolder()");
    CardView filmCardView = (CardView)filmView;
    filmCardView.setCardElevation(3*FilmographyActivity.NEW_CARD_COUNTER++);
    return new FilmographyViewHolder(filmView);
  }

  @Override
  public void onBindViewHolder(FilmographyViewHolder holder, final int position) {
    Log.d("FilmographyAdaper","onBindViewHolder()");
    final Film film = films.get(position);
    holder.filmTitre.setText(film.getTitre());
    holder.filmGenre.setText(film.getGenre());
    holder.filmAnnee.setText(String.valueOf(film.getAnnee()));
    holder.filmResume.setText(film.getResume());
    holder.view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("FilmographyAdapter","Item 'clicked'");
        activity.clickItem(film);
      }
    });

    if (positionsExpanded.contains(position)) {
      holder.filmResume.setVisibility(View.VISIBLE);
    } else {
      holder.filmResume.setVisibility(View.GONE);
    }

    holder.view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        Log.d("FilmographyAdapter","Item 'long clicked'");
        TextView resume = (TextView) v.findViewById(R.id.tv_film_resume);
        TextView resumeLabel = (TextView) v.findViewById(R.id.tv_film_resume_label);
        if (positionsExpanded.contains(position)) {
          resume.setVisibility(View.GONE);
          resumeLabel.setVisibility(View.GONE);
          positionsExpanded.remove(new Integer(position));
        } else {
          resume.setVisibility(View.VISIBLE);
          resumeLabel.setVisibility(View.VISIBLE);
          positionsExpanded.add(position);
        }
        return true;
      }
    });
  }

  class FilmographyViewHolder extends RecyclerView.ViewHolder {

    private final View view;

    private final TextView filmTitre;
    private final TextView filmGenre;
    private final TextView filmAnnee;
    private final TextView filmResume;

    public FilmographyViewHolder(View view) {
      super(view);
      Log.d("FilmographyViewHolder","FilmographyViewHolder()");
      this.view = view;
      filmTitre = (TextView) view.findViewById(R.id.tv_film_title);
      filmGenre = (TextView) view.findViewById(R.id.tv_film_genre);
      filmAnnee = (TextView) view.findViewById(R.id.tv_film_annee);
      filmResume = (TextView) view.findViewById(R.id.tv_film_resume);
    }
  }

}
