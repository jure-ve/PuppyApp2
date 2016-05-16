package com.jure.puppyapp2.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jure.puppyapp2.Classes.Puppy;
import com.jure.puppyapp2.R;

import java.util.List;

/**
 * Created by jure on 07/05/16.
 */
public class FiveAdapter extends RecyclerView.Adapter<FiveAdapter.PuppyViewHolder> {
    private List<Puppy> items;

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

    public FiveAdapter(List<Puppy> items) {
        this.items = items;
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

    }
}
