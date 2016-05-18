package com.jure.puppyapp2.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jure.puppyapp2.Adapters.PuppyAdapter;
import com.jure.puppyapp2.Classes.Puppy;
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

        List items = new ArrayList();

        items.add(new Puppy("Russy", R.drawable.p001));
        items.add(new Puppy("Goloso", R.drawable.p002));
        items.add(new Puppy("Genius", R.drawable.p003));
        items.add(new Puppy("Kike", R.drawable.p004));
        items.add(new Puppy("Pochito", R.drawable.p005));
        items.add(new Puppy("Galan", R.drawable.p006));
        items.add(new Puppy("Duque", R.drawable.p007));
        items.add(new Puppy("Donatto", R.drawable.p008));

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
