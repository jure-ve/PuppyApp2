package com.jure.puppyapp2.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jure.puppyapp2.Classes.Puppy;
import com.jure.puppyapp2.Adapters.PuppyAdapter;
import com.jure.puppyapp2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PuppyAdapter adapter;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private List items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.footprint2);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Hiciste Click en este botón!", Toast.LENGTH_SHORT).show();
            }
        });

        items = new ArrayList();

        items.add(new Puppy("Russy", R.drawable.p001));
        items.add(new Puppy("Goloso", R.drawable.p002));
        items.add(new Puppy("Genius", R.drawable.p003));
        items.add(new Puppy("Kike", R.drawable.p004));
        items.add(new Puppy("Pochito", R.drawable.p005));
        items.add(new Puppy("Galan", R.drawable.p006));
        items.add(new Puppy("Duque", R.drawable.p007));
        items.add(new Puppy("Donatto", R.drawable.p008));

        recycler = (RecyclerView) findViewById(R.id.recycler_view);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new PuppyAdapter(items);

        recycler.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_acerca) {

            View messageView = getLayoutInflater().inflate(R.layout.dialogo_acerca, null, false);

            AlertDialog.Builder dialogo_acerca = new AlertDialog.Builder(MainActivity.this);
            dialogo_acerca.setIcon(R.drawable.footprint_black);
            dialogo_acerca.setTitle(R.string.app_name);
            dialogo_acerca.setView(messageView);
            dialogo_acerca.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    dialog.dismiss();

                }
            });
            dialogo_acerca.create();

            dialogo_acerca.show();

            return true;
        }

        if (id == R.id.action_contacto) {
            Intent intent = new Intent(MainActivity.this, ContactoActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_five_puppies) {

            ArrayList<Puppy> favoritos = adapter.getFavoritos();

            if (favoritos.size() == 5) {
                Intent intent = new Intent(MainActivity.this, FiveActivity.class);

                intent.putExtra("favoritos", favoritos);

                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "¡Aun no tienes 5 favoritos!", Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
