package com.example.thagadur.bakingappudacity.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.callback.RecipeCategoryCallback;
import com.example.thagadur.bakingappudacity.holder.RecipeCategoryHolder;
import com.example.thagadur.bakingappudacity.model.Category;

import java.util.ArrayList;


public class RecipeCategoryAdapter extends RecyclerView.Adapter<RecipeCategoryHolder> {

    private ArrayList<Category> serviceCategoryList;

    private Context context;
    private RecipeCategoryCallback mCallback;

    public RecipeCategoryAdapter(ArrayList<Category> serviceCategoryList, Context context, RecipeCategoryCallback callback) {
        this.serviceCategoryList = serviceCategoryList;
        this.context = context;
        this.mCallback = callback;
    }

    @Override
    public RecipeCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecipeCategoryHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_category,
                        parent, false), mCallback);
    }

    @Override
    public void onBindViewHolder(RecipeCategoryHolder holder, int position) {

        String recipeName = serviceCategoryList.get(position).getmRecipeName();
        holder.mTextViewServiceCategory.setText(recipeName);

        if (!serviceCategoryList.get(position).getmImage().isEmpty()) {
            Glide.with(context).load(serviceCategoryList.get(position).getmImage()).into(holder.img_recipe);
        } else {
            holder.img_recipe.setImageResource(R.drawable.nutella);
            holder.img_recipe.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return serviceCategoryList.size();

    }

}

