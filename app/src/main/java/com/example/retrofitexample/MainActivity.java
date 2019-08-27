package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.example.retrofitexample.adapter.RecyclerAdapter;
import com.example.retrofitexample.api.PlaceHolderApi;
import com.example.retrofitexample.databinding.MainActivityDataBinding;
import com.example.retrofitexample.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String TAG = "MainActivity";


    MainActivityDataBinding activityDataBinding;

    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderApi placeHolderApi = retrofit.create(PlaceHolderApi.class);

        Call<List<Post>> call = placeHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                activityDataBinding.recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerAdapter = new RecyclerAdapter();
                activityDataBinding.recycler.setAdapter(recyclerAdapter);
                recyclerAdapter.setData(posts);

                activityDataBinding.recycler.setHasFixedSize(true);


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
