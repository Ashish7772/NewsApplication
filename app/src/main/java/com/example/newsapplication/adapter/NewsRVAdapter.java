package com.example.newsapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplication.R;
import com.example.newsapplication.activity.OnNewElementCLick;
import com.example.newsapplication.modelsclasses.Articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;
    private Context context;
    OnNewElementCLick onNewElementCLick;

    public NewsRVAdapter(ArrayList<Articles> articlesArrayList, Context context, OnNewElementCLick onNewElementCLick) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
        this.onNewElementCLick = onNewElementCLick;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
      return new NewsRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVAdapter.ViewHolder holder, int position) {

        Articles articles = articlesArrayList.get(position);
        holder.subTitleTV.setText(articles.getDescription());
        holder.titleTV.setText(articles.getTitle());

        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onNewElementCLick.onNewElementCLick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV,subTitleTV;
        private ImageView newsIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subTitleTV =  itemView.findViewById(R.id.idTVSubTitle);
            newsIV =  itemView.findViewById(R.id.idIVNews);
        }
    }
}
