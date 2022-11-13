package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    ListView listViewActivities;
    ArrayAdapter<String> adapter;
    String [] arrayPeliculas={"Yoga and Pilates", "Fitness and performance training",
                              "Bootcamp and Crossflit","Dance ,Pole and Barre","Swimming"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        int id=item.getItemId();
        if(id==R.id.done){
            String itemSelected="Selected item : \n";

            for(int i=0;i<listViewActivities.getCount();i++){

                if(listViewActivities.isItemChecked(i)){
                    itemSelected += listViewActivities.getItemAtPosition(i)+"\n";
                }
            }
            Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}