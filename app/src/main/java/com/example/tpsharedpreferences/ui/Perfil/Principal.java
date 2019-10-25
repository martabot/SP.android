package com.example.tpsharedpreferences.ui.Perfil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpsharedpreferences.R;
import com.example.tpsharedpreferences.model.Persona;
import com.example.tpsharedpreferences.ui.Login.MainActivity;

import java.util.regex.Pattern;

public class Principal extends AppCompatActivity {
    private TextView dni,mail,nombre,apellido,pass;
    private ViewModelPrincipal vmp;
    private Persona p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        inicializar();

        if(getIntent().hasExtra("Persona")){
            vmp.leer(getApplicationContext());
        }
    }

    public void inicializar(){
        dni=findViewById(R.id.tbdni);
        mail=findViewById(R.id.tbmail);
        nombre=findViewById(R.id.tbnombre);
        apellido=findViewById(R.id.tbapellido);
        pass=findViewById(R.id.tbpassword);
        vmp= ViewModelProviders.of(this).get(ViewModelPrincipal.class);
        vmp.getMldPersona().observe(this, new Observer<Persona>() {
            @Override
            public void onChanged(Persona persona) {
                if(p!=null){
                    persona=p;
                }
                dni.setText(persona.getDni()+"");
                mail.setText(persona.getMail());
                nombre.setText(persona.getNombre());
                apellido.setText(persona.getApellido());
                pass.setText(persona.getPassword());
            }
        });
    }

    public void guardar(View v){
        if(nombre.getText()!=null&&apellido.getText()!=null&&pass.getText()!=null){
            if(!validarEmail(mail.getText()+"")){
                Toast.makeText(this,"Mail invalido!",Toast.LENGTH_LONG).show();
            } else {
                if(dni.getText().length()!=8){
                    Toast.makeText(this,"DNI invalido!",Toast.LENGTH_LONG).show();
                } else {
                    vmp.guardar(getApplicationContext(),
                            new Persona(
                                    nombre.getText()+"",
                                    apellido.getText()+"",
                                    mail.getText()+"",
                                    pass.getText()+"",
                                    Long.parseLong(dni.getText()+"")));
                    Toast.makeText(this,"Datos guardados.",Toast.LENGTH_LONG).show();
                }
            }

        } else {
            Toast.makeText(this,"Hay campos vacios!",Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void cerrarSesion(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
