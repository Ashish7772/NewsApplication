package com.example.newsapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.newsapplication.R;
import com.example.newsapplication.modelsclasses.Articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class viewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Articles> articlesArrayList3;
    private int position2;

    public viewPagerAdapter(Context context, ArrayList<Articles> articlesArrayList3, int position) {
        this.context = context;
        this.articlesArrayList3 = articlesArrayList3;
        this.position2 = position;
    }

    @Override
    public int getCount() {
        return articlesArrayList3.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view  = LayoutInflater.from(context).inflate(R.layout.viewpagerdesign,container,false);
        ImageView idIVNews = view.findViewById(R.id.idIVNews);
        TextView idTVTitle = view.findViewById(R.id.idTVTitle);
        TextView idTVSubDesc = view.findViewById(R.id.idTVSubDesc);
        TextView idTVContent = view.findViewById(R.id.idTVContent);
        Button urlButton = view.findViewById(R.id.idBtnReadNews);

        Articles articles = articlesArrayList3.get(position);
      //  position2=position2+1;
        String title = articles.getTitle();
        String Des = articles.getDescription();
        String image = articles.getUrlToImage();
        String content = articles.getContent();
        String url = articles.getUrl();

        idTVTitle.setText(title);
        idTVSubDesc.setText(Des);
        idTVContent.setText(content);
        Picasso.get().load(image).into(idIVNews);

        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
