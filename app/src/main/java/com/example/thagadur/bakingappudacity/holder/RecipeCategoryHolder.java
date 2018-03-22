package com.example.thagadur.bakingappudacity.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.callback.RecipeCategoryCallback;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecipeCategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mTextViewServiceCategory;
    public LinearLayout recipelayoutt;
    public CircleImageView img_recipe;
    private RecipeCategoryCallback mCallback;

    public RecipeCategoryHolder(View itemView, RecipeCategoryCallback callback) {
        super(itemView);
        mTextViewServiceCategory = itemView.findViewById(R.id.txt_service_category);
        recipelayoutt = itemView.findViewById(R.id.recipelayoutt);
        img_recipe = itemView.findViewById(R.id.img_recipe);
        mCallback = callback;
        recipelayoutt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == recipelayoutt) {
            mCallback.onServiceCategoryClick(getLayoutPosition());
        }
    }
}
