package com.example.tpsharedpreferences.ui.Login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpsharedpreferences.model.Persona;
import com.example.tpsharedpreferences.request.ApiClient;

public class ViewModelMain extends ViewModel {
    private MutableLiveData<Persona> mldPersona;
    private ApiClient apiClient=new ApiClient();
    private Persona p;

    public LiveData<Persona> getMldPersona(){
        if(mldPersona==null){
            mldPersona=new MutableLiveData<>();
        }
        return mldPersona;
    }

    public void ingresar(Context context,String pass, String mail){
        p=apiClient.login(context,mail,pass);
        mldPersona.setValue(p);
    }
}
