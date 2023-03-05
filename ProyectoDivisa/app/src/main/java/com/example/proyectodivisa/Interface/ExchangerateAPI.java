package com.example.proyectodivisa.Interface;

import com.example.proyectodivisa.Model.Posts;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ExchangerateAPI {
    @GET("v6/064e537b97fd03303fa8e8ae/latest/USD")
    Call<Posts> getPosts();
}
