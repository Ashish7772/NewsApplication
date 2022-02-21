package com.example.newsapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapplication.R;
import com.example.newsapplication.adapter.NewsRVAdapter;
import com.example.newsapplication.modelsclasses.Articles;
import com.example.newsapplication.modelsclasses.NewsModal;
import com.example.newsapplication.retrofit.RetrofitAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity implements OnNewElementCLick{
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private RecyclerView newsRV;
    private NewsRVAdapter newsRVAdapter;
    String getCategory;
    private TextView toolbarText;

    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        swipeRefreshLayout  =findViewById(R.id.swipeRefreshNews);
        newsRV = findViewById(R.id.idRVNews);
        loadingPB = findViewById(R.id.idPBLoading);
        toolbarText = findViewById(R.id.toolbarText);
        articlesArrayList = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,MainActivity2.this,this);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);

        getCategory = getIntent().getStringExtra("category");
        toolbarText.setText(getCategory+" News");
        getNews(getCategory);
        newsRVAdapter.notifyDataSetChanged();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews(getCategory);
                newsRVAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines/?country=in&category="+ category +"&apiKey=45d4c40b432547cf8fdde736808c5d7b";
        String url = "https://newsapi.org/v2/top-headlines/?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=45d4c40b432547cf8fdde736808c5d7b";
        String BASE_URL = "https://newsapi.org/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if (category.equals("All")){
            call = retrofitAPI.getAllNews(url);
        }
        else {
            call = retrofitAPI.getAllNewsByCategory(categoryURL);
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModal.getArticles();
                for (int i=0; i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),
                            articles.get(i).getUrl(),articles.get(i).getContent()));
                }
                newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Fail to get news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNewElementCLick(int position) {
                        Intent i = new Intent(this, NewsDetailActivity.class);
//                i.putExtra("title",articlesArrayList.get(holder.getAdapterPosition()).getTitle());
//                i.putExtra("content",articlesArrayList.get(holder.getAdapterPosition()).getContent());
//                i.putExtra("desc",articlesArrayList.get(holder.getAdapterPosition()).getDescription());
//                i.putExtra("image",articlesArrayList.get(holder.getAdapterPosition()).getUrlToImage());
//                i.putExtra("url",articlesArrayList.get(holder.getAdapterPosition()).getUrl());

                i.putExtra("mylist", articlesArrayList);
                i.putExtra("position",position);
                startActivity(i);
    }
}