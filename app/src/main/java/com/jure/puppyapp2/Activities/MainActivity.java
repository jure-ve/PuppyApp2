package com.jure.puppyapp2.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.jure.puppyapp2.Adapters.PageAdapter;
import com.jure.puppyapp2.Classes.Puppy;
import com.jure.puppyapp2.Fragments.PuppiesListFragment;
import com.jure.puppyapp2.Fragments.PuppyPerfilFragment;
import com.jure.puppyapp2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Puppy> favoritos = new ArrayList<Puppy>();

    private int[] tabIcons = {
            R.drawable.home,
            R.drawable.dog,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.footprint2);
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Hiciste Click en este botón!", Toast.LENGTH_SHORT).show();
                }
            });

        }

        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);

        if (viewpager != null) {

            viewpager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));

        }

        TabLayout tablayout = (TabLayout) findViewById(R.id.tablayout);

        if (tablayout != null) {

            tablayout.setupWithViewPager(viewpager);
            tablayout.getTabAt(0).setIcon(tabIcons[0]);
            tablayout.getTabAt(1).setIcon(tabIcons[1]);

        }

    }
    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new PuppiesListFragment());
        fragments.add(new PuppyPerfilFragment());

        return fragments;
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

    public void setFavoritos(ArrayList<Puppy> favoritos) {
        this.favoritos = favoritos;
    }

}
