package com.jure.puppyapp2.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jure.puppyapp2.Classes.Puppy;
import com.jure.puppyapp2.R;

import java.util.ArrayList;

/**
 * Created by jure on 5/29/16.
 */
public class PuppyDB extends SQLiteOpenHelper {
    private Context context;

    public PuppyDB(Context context) {
        super(context, ConstantesDB.DATABASE_NAME, null, ConstantesDB.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaContacto = "CREATE TABLE " + ConstantesDB.TABLE_PUPPIES + "(" +
                ConstantesDB.TABLE_PUPPIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLE_PUPPIES_NOMBRE + " TEXT, " +
                ConstantesDB.TABLE_PUPPIES_IMAGEN + " INTEGER" +
                ")";
        String queryCrearTablaLikesContacto = "CREATE TABLE " + ConstantesDB.TABLE_RAITING_PUPPIES + "(" +
                ConstantesDB.TABLE_RAITING_PUPPIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TABLE_RAITING_PUPPIES_ID_PUPPY + " INTEGER, " +
                ConstantesDB.TABLE_RAITING_PUPPIES_NUMERO + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesDB.TABLE_RAITING_PUPPIES_ID_PUPPY + ") " +
                "REFERENCES " + ConstantesDB.TABLE_PUPPIES + "(" + ConstantesDB.TABLE_PUPPIES_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaContacto);

        db.execSQL(queryCrearTablaLikesContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesDB.TABLE_PUPPIES);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesDB.TABLE_RAITING_PUPPIES);
        onCreate(db);
    }

    public ArrayList<Puppy> obtenerPuppies() {
        ArrayList<Puppy> puppyList = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesDB.TABLE_PUPPIES;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Puppy puppy = new Puppy(
                registros.getInt(0),
                registros.getString(1),
                registros.getInt(2)
            );

            String queryRaiting = "SELECT COUNT("+ ConstantesDB.TABLE_RAITING_PUPPIES_NUMERO +") as likes " +
                    " FROM " + ConstantesDB.TABLE_RAITING_PUPPIES +
                    " WHERE " + ConstantesDB.TABLE_RAITING_PUPPIES_ID_PUPPY + "=" + puppy.getId();

            Cursor registrosRaiting = db.rawQuery(queryRaiting, null);
            if (registrosRaiting.moveToNext()){
                puppy.setRaiting(registrosRaiting.getInt(0));
            }else {
                puppy.setRaiting(0);
            }
            registrosRaiting.close();
            puppyList.add(puppy);

        }

        registros.close();
        db.close();

        return puppyList;
    }

    public void insertarPuppy(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLE_PUPPIES,null, contentValues);
        db.close();
    }

    public void insertarRaitingPuppy(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesDB.TABLE_RAITING_PUPPIES, null, contentValues);
        db.close();
    }


    public int obtenerRaitingPuppy(Puppy puppy){
        int raiting = 0;

        String query = "SELECT COUNT("+ ConstantesDB.TABLE_RAITING_PUPPIES_NUMERO +") " +
                "FROM " + ConstantesDB.TABLE_RAITING_PUPPIES + " " +
                "WHERE " + ConstantesDB.TABLE_RAITING_PUPPIES_ID_PUPPY + "="+puppy.getId();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            raiting = registros.getInt(0);
        }

        registros.close();

        db.close();

        return raiting;
    }

    public boolean puppiesVacio() {

        Boolean TablaVacia = true;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ ConstantesDB.TABLE_PUPPIES, null);

        if (cursor.getCount() > 0) {
            TablaVacia = false;
        }
        cursor.close();
        db.close();
        return TablaVacia;
    }

    public ArrayList<Puppy> obtenerPuppiesFavoritos() {
        ArrayList<Puppy> puppyList = new ArrayList<>();

        String query =  "SELECT * FROM " + ConstantesDB.TABLE_PUPPIES + " "+
                        "WHERE "+ConstantesDB.TABLE_PUPPIES_ID + " IN (" +
                        "SELECT "+ConstantesDB.TABLE_RAITING_PUPPIES_ID_PUPPY +" FROM "+ConstantesDB.TABLE_RAITING_PUPPIES + " "+
                        "GROUP BY "+ConstantesDB.TABLE_RAITING_PUPPIES_ID_PUPPY+" ORDER BY "+ConstantesDB.TABLE_RAITING_PUPPIES_ID_PUPPY+" DESC "+
                        "LIMIT 5)";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Puppy puppy = new Puppy(
                    registros.getInt(0),
                    registros.getString(1),
                    registros.getInt(2)
            );

            String queryRaiting = "SELECT COUNT("+ ConstantesDB.TABLE_RAITING_PUPPIES_NUMERO +") as likes " +
                    " FROM " + ConstantesDB.TABLE_RAITING_PUPPIES +
                    " WHERE " + ConstantesDB.TABLE_RAITING_PUPPIES_ID_PUPPY + "=" + puppy.getId();

            Cursor registrosRaiting = db.rawQuery(queryRaiting, null);
            if (registrosRaiting.moveToNext()){
                puppy.setRaiting(registrosRaiting.getInt(0));
            }else {
                puppy.setRaiting(0);
            }
            registrosRaiting.close();
            puppyList.add(puppy);

        }
        registros.close();
        db.close();

        return puppyList;

    }
}



