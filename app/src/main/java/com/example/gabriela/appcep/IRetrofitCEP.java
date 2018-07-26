package com.example.gabriela.appcep;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRetrofitCEP {

    @GET("/ws/{cep}/json/")
    Call<Endereco>getCep(@Path("cep")String cep);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://viacep.com.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
