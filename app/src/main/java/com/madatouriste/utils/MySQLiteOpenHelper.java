package com.madatouriste.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

 private String dropTable = "DROP TABLE IF EXISTS profil;";
 private String creationTableProfil = "CREATE TABLE profil (" +
         "datemesure Text PRIMARY KEY, " +
         "poids INTEGER NOT NULL," +
         "taille INTEGER NOT NULL," +
         "age INTEGER NOT NULL," +
         "sexe INTEGER NOT NULL" +
         ");";
 /**
  * Constructeur de Sqliteopenhelper
  * @param context
  * @param name
  * @param factory
  * @param version
  */
 public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
  super(context, name, factory, version);
 }

 /**
  * Effectuer  si il y a un changement de DB (executer une seule fois)
  * @param db
  */
 @Override
  public void onCreate(SQLiteDatabase db) {
//  db.execSQL(dropTable);
    db.execSQL(creationTableProfil); // il va verifier si la table profil est deja la il n'execute pas la requette
  }

 /**
  * Effectuer s'il ya un changement de version
  * @param db
  * @param oldVersion
  * @param newVersion
  */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
