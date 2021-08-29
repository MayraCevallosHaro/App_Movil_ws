package com.example.app_movil_ws.WebServices;

import com.example.smartmeter.Modelo.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Usuarios {


    @POST("application/json")
    Call<Usuario> saveuser(@Body Usuario userRequest);

}
