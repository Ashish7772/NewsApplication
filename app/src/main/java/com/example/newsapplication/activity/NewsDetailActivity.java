package com.example.newsapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapplication.R;
import com.example.newsapplication.adapter.viewPagerAdapter;
import com.example.newsapplication.modelsclasses.Articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsDetailActivity extends AppCompatActivity {

    String title,desc,content,imageURL,url;
    ViewPager viewPager;
    int position;
    private viewPagerAdapter viewPagerAdapter;
    private ArrayList<Articles> articlesArrayList2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = getIntent().getStringExtra("title");

        articlesArrayList2 = getIntent().getParcelableArrayListExtra("mylist");
        position =  getIntent().getIntExtra("position", 0);
//        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
        viewPager = findViewById(R.id.viewPager);
        loadpages();
    }

    private void loadpages() {

        viewPagerAdapter = new viewPagerAdapter(this,articlesArrayList2,position);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
        viewPagerAdapter.notifyDataSetChanged();
    }
}