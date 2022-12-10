package com.example.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dao.DBHelper;


public class Home extends AppCompatActivity {
    ListView listViewActivities;
    ArrayAdapter<String> adapter;
    String [] arrayPeliculas={"Yoga and Pilates", "Fitness and performance training", "Bootcamp and Crossflit","Dance ,Pole and Barre","Swimming"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*rien que adapter les donnees par lesquelles on a initialise le tableau 'arrayPeliculas' avec la listeView 'listViewActivities' par intermédiaire d'un adapter  */
        listViewActivities=findViewById(R.id.Activities_choice);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,arrayPeliculas);

        listViewActivities.setAdapter(adapter);
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
    /* On insere les items selectionnés dans la base de données et On passe les afficheer  */
        int id=item.getItemId();
        String  itemSelected="";
        DBHelper db=new DBHelper(this);
        if(id==R.id.done){
            for(int i=0;i<listViewActivities.getCount();i++){

                if(listViewActivities.isItemChecked(i)){
                    itemSelected +="\n"+listViewActivities.getItemAtPosition(i);
                    db.insertDataListChoice(itemSelected);
                }
            }
            Intent intent=new Intent(getApplicationContext(), choices.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}