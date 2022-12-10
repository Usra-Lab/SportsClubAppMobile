package com.example.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.dao.DBHelper;
import com.example.myapplication.models.Users;


public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText username=(EditText) findViewById(R.id.username);
        EditText password=(EditText) findViewById(R.id.password);
        EditText re_password=(EditText) findViewById(R.id.re_password);
        Button registerbtn=(Button) findViewById(R.id.registerbtn);
        TextView signaccount=(TextView) findViewById(R.id.signaccount);

        DBHelper DB=new DBHelper(this);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String re_pass=re_password.getText().toString();
                Users User=new Users(user,pass);

                //si les champ sont vides on declenche un Toast "please enter all fields"
                if(User.getUsername().equals("")||User.getPassword().equals("")||re_pass.equals("")){
                    Toast.makeText(Register.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                }

                /*sinon on verifie si l'utilisateur n'existe pas pour povoir l'inserer pour la 1ère fois et la dernière
                le username  doit etre unique,avec la contrainte que le re-password sera égale au password*/
                else{
                    if(User.getPassword().equals(re_pass)){
                        Boolean checkuser=DB.checkusername(User.getUsername());
                        if(checkuser==false){
                            Boolean insert=DB.insertData(User.getUsername(),User.getPassword());

                            //on verifie si on a bien insere l'utilisateur
                            //si oui on passe vers la page Home
                            if(insert==true){
                                Toast.makeText(Register.this, "Registred successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                            }

                            //si non on a pas arriver a l'inserer  ou bien il est déjà existant ou le re-password et le password ne sont pas les memes.
                            else{
                                Toast.makeText(Register.this, "Registred failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Register.this, "User already exists !! Sign In", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // si on  a un compte on passe pour faire l'authentification
        signaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }
}


