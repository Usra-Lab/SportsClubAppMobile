package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récuperation des données entrées par l'utilisateur
        EditText username=(EditText) findViewById(R.id.username);
        EditText password=(EditText) findViewById(R.id.password);
        Button signbtn=( Button ) findViewById(R.id.signbtn);
        TextView createacount=(TextView ) findViewById(R.id.createaccount);

        //Declaration d'un objet de type DBHleper pour pouvoir utiliser les methodes implémentées de cette classe
        DBHelper DB=new DBHelper(this);


        /*Une fois le botton signbtn est clické
        faisant une convertion de type pour les données déjà recuperées toString pour qu'on puisse les utilisé sous format String
        et on traite les contraintes*/
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

               //si les deux champs sont vides on déclanche un Toast "Please enter all fields"
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }

                //le si non c-à-dire que les champs ne sont pas vides alors on fait le teste avec la fct checkusernamepassword(user,pass)
                else{
                    Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                    //si la fct return true alors les données sont existant dans la base de données on peut passé à la page Home
                    if (checkuserpass==true){
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);

                    }
                    //si non il y a qlq chose qui ne va pas ou bien l' usename  est faut ou bien le password ou les deux
                    else{
                        Toast.makeText(MainActivity.this, "username or password wrong try again", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        // si on  a pas de compte on doit faire une registration et passant vers autre vue
        createacount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);

            }
        });

    }
}