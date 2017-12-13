package fr.eseo.dis.woodward.filmography;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.eseo.dis.woodward.filmography.data.RoleWithActor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rwoodward on 16/11/17.
 */

class FilmRoleAdapter extends RecyclerView.Adapter<FilmRoleAdapter.ActeurViewHolder> {


  private final Context context;
  private List<RoleWithActor> roles;

  public FilmRoleAdapter(Context context, int idFilm) {
    this.context = context;
    roles = new ArrayList<>();
  }

  public int getItemCount() {
    return roles.size();
  }

  public ActeurViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View acteurView = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.list_roles_card, viewGroup, false);
    return new ActeurViewHolder(acteurView);
  }


  public void onBindViewHolder(ActeurViewHolder acteurViewHolder, int i) {
    RoleWithActor role = roles.get(i);
    acteurViewHolder.acteurNom
        .setText(role.actors.get(0).getPrenom() + " " + role.actors.get(0).getNom());
    acteurViewHolder.acteurRole.setText(role.role.getNomRole());
  }

  public void setRoles(List<RoleWithActor> roles) {

    Log.d("DummyData", "addRoles");
    this.roles = roles;
  }

  class ActeurViewHolder extends RecyclerView.ViewHolder {

    private final TextView acteurNom;
    private final TextView acteurRole;

    public ActeurViewHolder(View view) {
      super(view);
      acteurNom = (TextView) view.findViewById(R.id.tv_role_acteur);
      acteurRole = (TextView) view.findViewById(R.id.tv_role_title);
    }
  }
}
