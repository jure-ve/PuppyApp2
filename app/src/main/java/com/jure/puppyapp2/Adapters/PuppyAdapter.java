package com.jure.puppyapp2.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jure.puppyapp2.Activities.MainActivity;
import com.jure.puppyapp2.Classes.Puppy;
import com.jure.puppyapp2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jure on 07/05/16.
 */
public class PuppyAdapter extends RecyclerView.Adapter<PuppyAdapter.PuppyViewHolder> {
    private List<Puppy> items;
    private ArrayList<Puppy> favoritos;
    private Context context;

    public static class PuppyViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView raiting;
        public ImageView imageView2;

        public PuppyViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            raiting = (TextView) v.findViewById(R.id.raiting);
            imageView2 = (ImageView) v.findViewById(R.id.imageView2);
        }

    }

    public PuppyAdapter(List<Puppy> items, Context context) {
        this.items = items;
        this.favoritos =  new ArrayList<Puppy>();
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PuppyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_puppy, viewGroup, false);
        return new PuppyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PuppyViewHolder viewHolder, final int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.raiting.setText(String.valueOf(items.get(i).getRaiting()));

        viewHolder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int raiting = items.get(i).getRaiting() + 1;
                items.get(i).setRaiting(raiting);
                viewHolder.raiting.setText(String.valueOf(items.get(i).getRaiting()));

                for (int z = 0; z < favoritos.size(); z++) {
                    if (favoritos.get(z).getNombre().equals(items.get(i).getNombre())) {
                        favoritos.remove(z);
                    }
                }

                favoritos.add(items.get(i));

                if (favoritos.size() > 5) {
                    favoritos.remove(0);
                }

                MainActivity ma = (MainActivity) context;

                ma.setFavoritos(favoritos);
            }
        });

        viewHolder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int raiting = items.get(i).getRaiting() + 1;
                items.get(i).setRaiting(raiting);
                viewHolder.raiting.setText(String.valueOf(items.get(i).getRaiting()));

                for (int z = 0; z < favoritos.size(); z++) {
                    if (favoritos.get(z).getNombre().equals(items.get(i).getNombre())) {
                        favoritos.remove(z);
                    }
                }

                favoritos.add(items.get(i));

                if (favoritos.size() > 5) {
                    favoritos.remove(0);
                }

                MainActivity ma = (MainActivity) context;

                ma.setFavoritos(favoritos);
            }

        });

    }
    public ArrayList<Puppy> getFavoritos() {
        return favoritos;
    }

}