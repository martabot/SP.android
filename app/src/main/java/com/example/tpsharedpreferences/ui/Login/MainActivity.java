package com.example.tpsharedpreferences.ui.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tpsharedpreferences.R;
import com.example.tpsharedpreferences.model.Persona;
import com.example.tpsharedpreferences.ui.Perfil.Principal;

public class MainActivity extends AppCompatActivity {
    private EditText mail,pass;
    private ViewModelMain vmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();

    }

    public void ingresar(View v){
        if(pass.getText()!=null&&mail.getText()!=null){
            vmm.ingresar(getApplicationContext(),pass.getText().toString(),mail.getText().toString());
        } else {
            Toast.makeText(this,"Hay campos vacíos!",Toast.LENGTH_LONG).show();
        }
    }

    public void registrarse(View v){
        Intent i = new Intent(getApplicationContext(),Principal.class);
        startActivity(i);
    }

    public void inicializar(){
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.password);
        vmm= ViewModelProviders.of(this).get(ViewModelMain.class);
        vmm.getMldPersona().observe(this, new Observer<Persona>() {
            @Override
            public void onChanged(Persona persona) {
                if(persona!=null){
                    Intent i = new Intent(getApplicationContext(), Principal.class);
                    i.putExtra("Persona", persona.getMail());
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Usuario no registrado.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
