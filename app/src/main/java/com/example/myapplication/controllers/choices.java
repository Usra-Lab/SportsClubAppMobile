package com.example.myapplication.controllers;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dao.DBHelper;


public class choices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choices);

       /* Affichage des choix insere dans un textView */
        TextView choices=(TextView)findViewById(R.id.choices);
        DBHelper db =new DBHelper(this);
        Cursor cursor=db.getdata();
        if (cursor.moveToFirst()) {
            do {
                choices.setText(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        Button delchoices=(Button)findViewById(R.id.delchoices) ;
        Button addchoices=(Button)findViewById(R.id.addchoices) ;

        /* Après click sur le botton delchoices on supprime les données dans la db et donc lorsqu'on va revenir à la vue on va rien trouver */
        delchoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deletechoices();
                Intent intent=new Intent(getApplicationContext(),choices.class);
                startActivity(intent);

            }
        });
        /* Après click sur le botton addchoices on revient à la page home et on insert à nouveau notre choix*/
        addchoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }
}