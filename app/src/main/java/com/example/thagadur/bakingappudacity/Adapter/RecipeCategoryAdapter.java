package com.example.thagadur.bakingappudacity.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.callback.RecipeCategoryCallback;
import com.example.thagadur.bakingappudacity.model.Category;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecipeCategoryAdapter extends RecyclerView.Adapter<RecipeCategoryAdapter.RecipeCategoryHolder> {

    private ArrayList<Category> recipeCategoryList;

    private Context context;
    private RecipeCategoryCallback recipeCategoryCallback;

    public RecipeCategoryAdapter(ArrayList<Category> recipeCategoryList, Context context, RecipeCategoryCallback callback) {
        this.recipeCategoryList = recipeCategoryList;
        this.context = context;
        this.recipeCategoryCallback = callback;
    }

    @Override
    public RecipeCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecipeCategoryHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_category,
                        parent, false), recipeCategoryCallback);
    }

    @Override
    public void onBindViewHolder(RecipeCategoryHolder holder, int position) {

        String recipeName = recipeCategoryList.get(position).getmRecipeName();
        holder.serviceCategoryTextView.setText(recipeName);

        if (!recipeCategoryList.get(position).getmImage().isEmpty()) {
            Glide.with(context).load(recipeCategoryList.get(position).getmImage()).into(holder.recipeImageView);
        } else {
            holder.recipeImageView.setImageResource(R.drawable.chocolate);
            holder.recipeImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return recipeCategoryList.size();

    }

    class RecipeCategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout recipeLinearlayout;
        public CircleImageView recipeImageView;
        public TextView serviceCategoryTextView;
        private RecipeCategoryCallback recipeCategoryCallback;

        public RecipeCategoryHolder(View itemView, RecipeCategoryCallback callback) {
            super(itemView);
            serviceCategoryTextView = itemView.findViewById(R.id.service_category_texview);
            recipeLinearlayout = itemView.findViewById(R.id.recipeLinearlayout);
            recipeImageView = itemView.findViewById(R.id.recipe_image_view);
            recipeCategoryCallback = callback;
            recipeLinearlayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == recipeLinearlayout) {
                recipeCategoryCallback.onServiceCategoryClick(getLayoutPosition());
            }
        }
    }
}

