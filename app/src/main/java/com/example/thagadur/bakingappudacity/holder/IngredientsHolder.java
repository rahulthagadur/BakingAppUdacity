package com.example.thagadur.bakingappudacity.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thagadur.bakingappudacity.R;


public class IngredientsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_ingredient,txt_quantity,txt_measure;

    LinearLayout ingredients_layout;
    public IngredientsHolder(View itemView) {
        super(itemView);
        txt_ingredient = itemView.findViewById(R.id.txt_ingredient);
        txt_quantity = itemView.findViewById(R.id.txt_quantity);
        txt_measure = itemView.findViewById(R.id.txt_measure);
        ingredients_layout = itemView.findViewById(R.id.ingredients_layout);


    }

    @Override
    public void onClick(View view) {
    }
}
