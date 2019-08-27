package com.example.retrofitexample.api;

import com.example.retrofitexample.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();
}
