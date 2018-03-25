package com.example.thagadur.bakingappudacity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.model.Ingrediants;

import java.util.ArrayList;

public class IngredientsRecipeAdapter extends RecyclerView.Adapter<IngredientsRecipeAdapter.IngredientsHolder> {

    private ArrayList<Ingrediants> ingrediantList;
    private Context context;

    public IngredientsRecipeAdapter(ArrayList<Ingrediants> serviceCategoryList, Context context) {
        this.ingrediantList = serviceCategoryList;
        this.context = context;
    }

    @Override
    public IngredientsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IngredientsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(IngredientsHolder holder, int position) {
        String quantity = ingrediantList.get(position).getQuantity();
        String measure = ingrediantList.get(position).getMeasure();
        String ingredient = ingrediantList.get(position).getIngredient();
        holder.ingredientTextview.setText(ingredient);
        holder.quantityTextview.setText(quantity);
        holder.measureTextview.setText(measure);
    }

    @Override
    public int getItemCount() {
        return ingrediantList.size();
    }

    class IngredientsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView ingredientTextview, quantityTextview, measureTextview;
        LinearLayout ingredientsLinearlayout;

        public IngredientsHolder(View itemView) {
            super(itemView);
            ingredientTextview = itemView.findViewById(R.id.txt_ingredient);
            quantityTextview = itemView.findViewById(R.id.txt_quantity);
            measureTextview = itemView.findViewById(R.id.txt_measure);
            ingredientsLinearlayout = itemView.findViewById(R.id.ingredients_layout);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
