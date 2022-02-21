package com.example.newsapplication.retrofit;

import com.example.newsapplication.modelsclasses.NewsModal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @GET
    Call<NewsModal> getAllNews(@Url String url);

    @GET
    Call<NewsModal> getAllNewsByCategory(@Url String url);

}
