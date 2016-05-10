package com.jure.puppyapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recycler;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager lManager;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        List items = new ArrayList();

        ArrayList<Puppy> ListaPuppy = (ArrayList<Puppy>)getIntent().getSerializableExtra("favoritos");

        Collections.reverse(ListaPuppy);

        for (Puppy puppy : ListaPuppy) {
            items.add(puppy);
        }

        recycler = (RecyclerView) findViewById(R.id.recycler_view_five);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);

        recycler.setLayoutManager(lManager);

        adapter = new FiveAdapter(items);

        recycler.setAdapter(adapter);
    }
}
