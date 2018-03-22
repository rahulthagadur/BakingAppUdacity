package com.example.thagadur.bakingappudacity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.callback.RecipeStepsCallback;
import com.example.thagadur.bakingappudacity.holder.RecipeStepsHolder;
import com.example.thagadur.bakingappudacity.model.RecipeSteps;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsHolder> {

    private List<RecipeSteps> mServiceCategoryList;
    private RecipeStepsCallback mCallback;
    private Context mContext;

    public RecipeStepsAdapter(Context context, List<RecipeSteps> serviceCategoryList, RecipeStepsCallback callback) {
        mContext = context;
        mServiceCategoryList = serviceCategoryList;
        mCallback = callback;
    }

    @Override
    public RecipeStepsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeStepsHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stepsdescription,
                        parent, false), mCallback);
    }

    @Override
    public void onBindViewHolder(RecipeStepsHolder holder, int position) {
        RecipeSteps serviceCategory = mServiceCategoryList.get(position);
        holder.txt_descriptiom.setText(serviceCategory.getmShortDescription());
        if (!serviceCategory.getmThumnailUrl().isEmpty()) {

            Glide.with(mContext).load(serviceCategory.getmThumnailUrl()).into(holder.order_image);
            holder.order_image.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.order_image.setImageResource(R.drawable.cook);
            holder.order_image.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return mServiceCategoryList.size();
    }
}
