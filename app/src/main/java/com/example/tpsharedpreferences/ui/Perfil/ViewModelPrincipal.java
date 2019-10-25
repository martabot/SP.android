package com.example.tpsharedpreferences.ui.Perfil;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.tpsharedpreferences.model.Persona;
import com.example.tpsharedpreferences.request.ApiClient;

public class ViewModelPrincipal extends ViewModel {
    private MutableLiveData<Persona> mldPersona;
    private MutableLiveData<String> datosPersona;
    private ApiClient apiClient=new ApiClient();
    private Persona p;

    public LiveData<Persona> getMldPersona(){
        if(mldPersona==null){
            mldPersona=new MutableLiveData<>();
        }
        return mldPersona;
    }

    public LiveData<String> getDatosPersona(){
        if(datosPersona==null){
            datosPersona=new MutableLiveData<>();
        }
        return datosPersona;
    }

    public void guardar(Context context, Persona persona){
        apiClient.guardar(context,persona);
    }

    public void leer(Context context){
        p=apiClient.leer(context);
        mldPersona.setValue(p);
    }

}
