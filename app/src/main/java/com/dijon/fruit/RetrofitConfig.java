package com.dijon.fruit;

import interfaces.FruitService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://tropicalfruitandveg.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public FruitService getFruitService() {
        return this.retrofit.create(FruitService.class);
    }
}
