package com.example.tpsharedpreferences.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.tpsharedpreferences.model.Persona;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if(sp==null){
            sp=context.getSharedPreferences("datos",0);
        }
        return sp;
    }

    public static void guardar(Context context, Persona persona){
        SharedPreferences sp=conectar(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("mail",persona.getMail());
        editor.putLong("dni",persona.getDni());
        editor.putString("nombre",persona.getNombre());
        editor.putString("apellido",persona.getApellido());
        editor.putString("password",persona.getPassword());
        editor.commit();
    }

    public static Persona leer(Context context){
        SharedPreferences sp=conectar(context);
        String email=sp.getString("mail","-1");
        Long dni=sp.getLong("dni",-1);
        String nombre=sp.getString("nombre","-1");
        String apellido=sp.getString("apellido","-1");
        String pass=sp.getString("password","-1");
        Persona persona=new Persona(nombre,apellido,email,pass,dni);
        return  persona;
    }

    public static Persona login(Context context,String mail,String password){
        Persona persona = null;
        SharedPreferences sp=conectar(context);
        Long dni= sp.getLong("dni",-1);
        String email=sp.getString("mail","-1");
        String nombre=sp.getString("nombre","-1");
        String apellido=sp.getString("apellido","-1");
        String pass= sp.getString("password","-1");
        if(mail.equals(email)&&password.equals(pass)){
            persona = new Persona(nombre,apellido,email,pass,dni);
        }
        return persona;
    }
}
