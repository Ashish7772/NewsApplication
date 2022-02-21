package com.example.newsapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.example.newsapplication.R;
import com.example.newsapplication.adapter.CategoryRVAdapter;
import com.example.newsapplication.modelsclasses.CategoryRVModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface{

    // 45d4c40b432547cf8fdde736808c5d7b


    private RecyclerView categoryRV;
    private ArrayList<CategoryRVModel> categoryRVModelArrayList;
    private CategoryRVAdapter categoryRVAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        categoryRV = findViewById(R.id.idRVCategories);
        categoryRVModelArrayList = new ArrayList<>();
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModelArrayList,this,this::onCategoryClick);
        categoryRV.setAdapter(categoryRVAdapter);

        getCategories();
    }



    private void getCategories(){
        categoryRVModelArrayList.add(new CategoryRVModel("All","https://media.istockphoto.com/photos/abstract-digital-news-concept-picture-id1290904409?b=1&k=20&m=1290904409&s=170667a&w=0&h=6khncht98kwYG-l7bdeWfBNs_GGcG1pDqzLb6ZXhh7I="));
        categoryRVModelArrayList.add(new CategoryRVModel("Technology","https://media.istockphoto.com/photos/online-news-in-mobile-phone-close-up-of-smartphone-screen-man-reading-picture-id1065782416?b=1&k=20&m=1065782416&s=170667a&w=0&h=DeacwxQl_KZdhtkk6eHxxGunzYuHLfi6S6rvM3CsVeg="));
        categoryRVModelArrayList.add(new CategoryRVModel("Science","https://media.istockphoto.com/photos/newspaper-with-the-headline-research-and-development-picture-id474852858?b=1&k=20&m=474852858&s=170667a&w=0&h=-WZQNv4d2gzj-z16RuHDah8-E9LjjhJoCCbWUD9xacM="));
        categoryRVModelArrayList.add(new CategoryRVModel("Sports","https://media.istockphoto.com/photos/sport-news-picture-id174859622?b=1&k=20&m=174859622&s=170667a&w=0&h=w5_bBTr7lRXImIG6SgzChxYyFaX1Xbt6K0VW9-kuPFw="));
        categoryRVModelArrayList.add(new CategoryRVModel("General","https://media.istockphoto.com/photos/headline-picture-id184625088?b=1&k=20&m=184625088&s=170667a&w=0&h=eU3WjzBxqfutz-71Lun8kP-FOKsBh6h2YYR6f1TFhYw="));
        categoryRVModelArrayList.add(new CategoryRVModel("Business","https://media.istockphoto.com/photos/crisis-in-news-picture-id147036034?b=1&k=20&m=147036034&s=170667a&w=0&h=AwPgqLUXvbBfH_WI_rzpVc1VNfaFCPJmvDrgourmMbE="));
        categoryRVModelArrayList.add(new CategoryRVModel("Entertainment","https://media.istockphoto.com/photos/multiple-television-screens-in-blue-tones-picture-id171588907?b=1&k=20&m=171588907&s=170667a&w=0&h=yzJufmRV5KvD4W9BVPGJvo1skGV5e1uNXumVATMr1mg="));
        categoryRVModelArrayList.add(new CategoryRVModel("Health","https://media.istockphoto.com/photos/newspaper-on-a-wooden-desk-health-and-medical-picture-id600170248?b=1&k=20&m=600170248&s=170667a&w=0&h=E0u0bzQrGu0Na1mu5cPDvGl3m5sy1wjwVOH2yppiAlM="));
        categoryRVAdapter.notifyDataSetChanged();
    }



    @Override
    public void onCategoryClick(int position) {
       String category = categoryRVModelArrayList.get(position).getCategory();
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }
}