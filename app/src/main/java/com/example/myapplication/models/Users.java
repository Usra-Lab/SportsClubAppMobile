package com.example.myapplication.models;

public class Users {
    private String username,password;
    public Users(String username,String password){
        this.username=username;
        this.password=password;
    }

    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
}
