package com.example.thagadur.bakingappudacity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.holder.IngredientsHolder;
import com.example.thagadur.bakingappudacity.model.Ingrediants;

import java.util.ArrayList;


public class IngredientsRecipeAdapter extends RecyclerView.Adapter<IngredientsHolder> {

    private ArrayList<Ingrediants> mServiceCategoryList;
    private Context context;

    public IngredientsRecipeAdapter(ArrayList<Ingrediants> mServiceCategoryList, Context context) {
        this.mServiceCategoryList = mServiceCategoryList;
        this.context = context;
    }

    @Override
    public IngredientsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IngredientsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(IngredientsHolder holder, int position) {

        String quantity = mServiceCategoryList.get(position).getmQuantity();
        String measure = mServiceCategoryList.get(position).getmMeasure();
        String ingredient = mServiceCategoryList.get(position).getmIngredient();

        Log.e("quantity", "" + quantity);
        holder.txt_quantity.setText(quantity);
        holder.txt_measure.setText(measure);
        holder.txt_ingredient.setText(ingredient);

    }

    @Override
    public int getItemCount() {
        return mServiceCategoryList.size();
    }

}
