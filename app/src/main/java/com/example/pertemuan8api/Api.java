package com.example.pertemuan8api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("fact")
    Call<Cat> getCatImage();
}
