package com.jure.puppyapp2.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jure.puppyapp2.Activities.MainActivity;
import com.jure.puppyapp2.Adapters.PuppyAdapter;
import com.jure.puppyapp2.Classes.Puppy;
import com.jure.puppyapp2.Modelos.ConstructorPuppies;
import com.jure.puppyapp2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PuppiesListFragment extends Fragment {
    private PuppyAdapter adapter;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;


    public PuppiesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_puppies_list, container, false);

        ConstructorPuppies constructorPuppies = new ConstructorPuppies(getActivity());

        List items = constructorPuppies.obtenerDatos();

        MainActivity ma = (MainActivity) getActivity();
        ma.setFavoritos(constructorPuppies.obtenerFavoritos());

        recycler = (RecyclerView) v.findViewById(R.id.puppieslist);

        if (recycler != null) {
            recycler.setHasFixedSize(true);
        }

        layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);

        adapter = new PuppyAdapter(items, getActivity());

        recycler.setAdapter(adapter);

        return v;
    }

    public ArrayList<Puppy> getPuppiesFavoritos(){
        return adapter.getFavoritos();
    }

}
