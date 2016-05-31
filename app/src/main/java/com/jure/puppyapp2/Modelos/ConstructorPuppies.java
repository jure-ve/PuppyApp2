package com.jure.puppyapp2.Modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.jure.puppyapp2.BaseDatos.ConstantesDB;
import com.jure.puppyapp2.BaseDatos.PuppyDB;
import com.jure.puppyapp2.Classes.Puppy;
import com.jure.puppyapp2.R;

import java.util.ArrayList;

/**
 * Created by jure on 5/29/16.
 */
public class ConstructorPuppies {

        private static final int RAITING = 1;
        private Context context;
        public ConstructorPuppies(Context context) {
            this.context = context;
        }

        public ArrayList<Puppy> obtenerDatos() {
            PuppyDB db = new PuppyDB(context);

            if (db.puppiesVacio()) {
                insertarOchoPuppies(db);
            }

            return  db.obtenerPuppies();
        }

        public ArrayList<Puppy> obtenerFavoritos() {
            PuppyDB db = new PuppyDB(context);
            return  db.obtenerPuppiesFavoritos();
        }


    public void darRaitingPuppy(Puppy puppy){
            PuppyDB db = new PuppyDB(context);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ConstantesDB.TABLE_RAITING_PUPPIES_ID_PUPPY, puppy.getId());
            contentValues.put(ConstantesDB.TABLE_RAITING_PUPPIES_NUMERO, RAITING);
            db.insertarRaitingPuppy(contentValues);
        }

        public int obtenerRaitingPuppy(Puppy puppy){
            PuppyDB db = new PuppyDB(context);
            return db.obtenerRaitingPuppy(puppy);
        }

    public void insertarOchoPuppies(PuppyDB db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PUPPIES_NOMBRE, "Russy");
        contentValues.put(ConstantesDB.TABLE_PUPPIES_IMAGEN, R.drawable.p001);

        db.insertarPuppy(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PUPPIES_NOMBRE, "Goloso");
        contentValues.put(ConstantesDB.TABLE_PUPPIES_IMAGEN, R.drawable.p002);

        db.insertarPuppy(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PUPPIES_NOMBRE, "Genius");
        contentValues.put(ConstantesDB.TABLE_PUPPIES_IMAGEN, R.drawable.p003);

        db.insertarPuppy(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PUPPIES_NOMBRE, "Kike");
        contentValues.put(ConstantesDB.TABLE_PUPPIES_IMAGEN, R.drawable.p004);

        db.insertarPuppy(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PUPPIES_NOMBRE, "Pochito");
        contentValues.put(ConstantesDB.TABLE_PUPPIES_IMAGEN, R.drawable.p005);

        db.insertarPuppy(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PUPPIES_NOMBRE, "Galan");
        contentValues.put(ConstantesDB.TABLE_PUPPIES_IMAGEN, R.drawable.p006);

        db.insertarPuppy(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PUPPIES_NOMBRE, "Duque");
        contentValues.put(ConstantesDB.TABLE_PUPPIES_IMAGEN, R.drawable.p007);

        db.insertarPuppy(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesDB.TABLE_PUPPIES_NOMBRE, "Donatto");
        contentValues.put(ConstantesDB.TABLE_PUPPIES_IMAGEN, R.drawable.p008);

        db.insertarPuppy(contentValues);

    }


}
