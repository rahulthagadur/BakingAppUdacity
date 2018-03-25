package com.example.thagadur.bakingappudacity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.callback.RecipeStepsCallback;
import com.example.thagadur.bakingappudacity.model.RecipeSteps;

import java.util.List;

import static com.example.thagadur.bakingappudacity.R.id.steps_layout;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepsHolder> {

    private List<RecipeSteps> recipeStepsList;
    private RecipeStepsCallback recipeStepsCallback;
    private Context context;

    public RecipeStepsAdapter(Context context, List<RecipeSteps> serviceCategoryList, RecipeStepsCallback callback) {
        this.context = context;
        this.recipeStepsList = serviceCategoryList;
        recipeStepsCallback = callback;
    }

    @Override
    public RecipeStepsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeStepsHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stepsdescription,
                        parent, false), recipeStepsCallback);
    }

    @Override
    public void onBindViewHolder(RecipeStepsHolder holder, int position) {
        RecipeSteps serviceCategory = recipeStepsList.get(position);
        holder.descriptionTextview.setText(serviceCategory.getmShortDescription());
        if (!serviceCategory.getmThumnailUrl().isEmpty()) {

            Glide.with(this.context).load(serviceCategory.getmThumnailUrl()).into(holder.orderImageView);
            holder.orderImageView.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.orderImageView.setImageResource(R.drawable.cook);
            holder.orderImageView.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return recipeStepsList.size();
    }

    class RecipeStepsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView descriptionTextview;
        public ImageView orderImageView;
        LinearLayout stepsLinearLayout;
        boolean twoPanel;
        private RecipeStepsCallback recipeStepsCallback;


        public RecipeStepsHolder(View itemView, RecipeStepsCallback callback) {
            super(itemView);
            descriptionTextview = itemView.findViewById(R.id.txt_descriptiom);
            orderImageView = itemView.findViewById(R.id.order_image);
            stepsLinearLayout = itemView.findViewById(steps_layout);

            recipeStepsCallback = callback;
            stepsLinearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == stepsLinearLayout) {
                recipeStepsCallback.onServiceCategoryClick(getLayoutPosition(), twoPanel);
            }
        }
    }

}
