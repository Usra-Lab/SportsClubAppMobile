
package com.example.myapplication.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//la class public DBHelper hérite de la classe abstraite SQLiteOpenHelper dont laquelle on doit implémenter deux méthodes onCreate(para) & onUpgrade(para1,para2,para3)
public class DBHelper extends SQLiteOpenHelper
{
    // constructeur
    public DBHelper(@Nullable Context context) {
        super(context,"login.db", null, 2);
    }

    //On commence à crée la table users avec les deux colonnes username & password / et execution de la requete à travers la méthode execSQL("requete") methode de la classe SQLiteDatabase par intermedière de l'objet db.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (username TEXT primary key,password TEXT)");
    }
    //Dans le cas de la mis à jour de la base de données "le changement de la version" on peut faire des drop/alter table ..etc
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("create table choice (despription TEXT)");
        onCreate(db);
    }

    /* insertion des données dans notre table users de la base de données "login.db" par le contenu qu'on a affecté à l'objet contentValues .
    C_à-dire que les données ne serons plus insérer directement dans la base de données mais par intermediare de l'objet contentValues
    de type ContentValues "Ce n'est qu'un Tableau associative ou bien ce qu'on appel Hashmap "*/

    public boolean insertData(String username ,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=db.insert("users",null,contentValues);
        if(result==-1)return false;
        else return true;
    }


    //fonction qui sert à verifier si le username existe déjà et donc le compte est déjà existant.
    public Boolean checkusername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select *from users where username=?", new String[]{username});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
    //fonction qui sert à vérifier si  on fait l'authentification avec le username et son password associé ,existant déjà dans la base de données
    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select *from users where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0)return true;
        else return false;
    }
    //fonction qui sert à inserer les données dans la table choice
    public boolean insertDataListChoice(String choice){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("despription",choice);
        long result=db.insert("choice",null,contentValues);
        if(result==-1)return false;
        else return true;
    }

    // fonction qui sert à retourner les données de la table choice
    public Cursor getdata(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from choice ",null);
        return cursor;
    }
    // fonction qui sert à supprimer les données de la table choice
    public void deletechoices(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("choice",null,null);
    }
}
