package com.example.thagadur.bakingappudacity.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.callback.RecipeStepsCallback;


public class RecipeStepsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_descriptiom;

    public ImageView order_image;
    LinearLayout  steps_layout;
    boolean Twopane;
    private RecipeStepsCallback mCallback;


    public RecipeStepsHolder(View itemView, RecipeStepsCallback callback) {
        super(itemView);
        txt_descriptiom = itemView.findViewById(R.id.txt_descriptiom);
        order_image = itemView.findViewById(R.id.order_image);
        steps_layout = itemView.findViewById(R.id.steps_layout);

        mCallback = callback;
        steps_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == steps_layout) {
            mCallback.onServiceCategoryClick(getLayoutPosition(), Twopane);
        }
    }
}
