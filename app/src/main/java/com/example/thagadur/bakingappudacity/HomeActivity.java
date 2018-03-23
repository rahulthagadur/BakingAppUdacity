package com.example.thagadur.bakingappudacity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.thagadur.bakingappudacity.Adapter.RecipeCategoryAdapter;
import com.example.thagadur.bakingappudacity.api.ApiClient;
import com.example.thagadur.bakingappudacity.api.CategoryService;
import com.example.thagadur.bakingappudacity.app.ErrorHandler;
import com.example.thagadur.bakingappudacity.app.SimpleIdlingResource;
import com.example.thagadur.bakingappudacity.app.ToastBuilder;
import com.example.thagadur.bakingappudacity.callback.RecipeCategoryCallback;
import com.example.thagadur.bakingappudacity.model.Category;
import com.example.thagadur.bakingappudacity.model.Ingrediants;
import com.example.thagadur.bakingappudacity.widgetsProvider.UpdateBakingService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity implements RecipeCategoryCallback {

    protected ArrayList<Ingrediants> ingrediants = new ArrayList<>();
    private boolean isTablet;

    private Context mContext;
    private RecyclerView recipeListRecyclerView;
    private ArrayList<Category> serviceCategoryList;
    private RecipeCategoryAdapter mAdapter;

    private ProgressDialog mProgressDialog;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialisationOfObjects();
        initRecyclerView();
        getListService();
        hideProgressDialog();
        getIdlingResource();
    }

    private void initialisationOfObjects() {
        mContext = this;
        recipeListRecyclerView = findViewById(R.id.recyclerview);
        isTablet = getResources().getBoolean(R.bool.isTablet);
        mProgressDialog = new ProgressDialog(mContext);
        serviceCategoryList = new ArrayList<>();
        mAdapter = new RecipeCategoryAdapter(serviceCategoryList, mContext, this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    private void initRecyclerView() {

        if (isTablet) {
            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
            recipeListRecyclerView.setLayoutManager(layoutManager);
            recipeListRecyclerView.addItemDecoration(
                    new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
            recipeListRecyclerView.setAdapter(mAdapter);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            recipeListRecyclerView.setLayoutManager(linearLayoutManager);
            recipeListRecyclerView.addItemDecoration(
                    new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
            recipeListRecyclerView.setAdapter(mAdapter);
        }


    }

    private void getListService() {
        showProgressDialog("Loading....");
        CategoryService categoryService = ApiClient.getClient().create(CategoryService.class);
        Call<ArrayList<Category>> call = categoryService.getRecipe();
        call.enqueue(new retrofit2.Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Category>> call, @NonNull Response<ArrayList<Category>> response) {
                hideProgressDialog();
                Log.e("arrayvalud", "" + response.toString());
                List<Category> CategoryListResponse = response.body();
                if (response.isSuccessful() && CategoryListResponse != null) {
                    hideProgressDialog();
                    serviceCategoryList.clear();
                    serviceCategoryList.addAll(CategoryListResponse);
                    mAdapter.notifyDataSetChanged();

                } else {


                    hideProgressDialog();
                    ErrorHandler.processError(mContext, response.code(), response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Category>> call, @NonNull Throwable t) {
                hideProgressDialog();
                Log.e("arrayvalud", "" + t.getMessage());
                ToastBuilder.build(mContext, t.getMessage());
            }
        });
    }

    @SuppressWarnings("SameParameterValue")
    private void showProgressDialog(String message) {
        mProgressDialog.setMessage(message);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    private void hideProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void onServiceCategoryClick(int position) {
        Intent intent = new Intent(this, RecipeDetailsActivity.class);

        ingrediants = serviceCategoryList.get(position).getmIngredients();
        intent.putParcelableArrayListExtra(getString(R.string.key_arrayingredients), serviceCategoryList.get(position).getmIngredients());
        intent.putParcelableArrayListExtra(getString(R.string.key_arrayrecipesteps), serviceCategoryList.get(position).getmSteps());
        intent.putExtra(getString(R.string.key_recipename), serviceCategoryList.get(position).getmRecipeName());

        final ArrayList<String> recipeIngredientForWidgets = new ArrayList<>();

        if (ingrediants != null) {
            for (int i = 0; i < ingrediants.size(); i++) {

                String ingredientName = ingrediants.get(i).getmIngredient();
                String quantity = ingrediants.get(i).getmQuantity();
                String measure = ingrediants.get(i).getmMeasure();

                recipeIngredientForWidgets.add(ingredientName + "\n" + "Quantity: " + quantity + "\n" + "Measure: " + measure + "\n");
            }

        }
        UpdateBakingService.startBakingService(mContext, recipeIngredientForWidgets);
        startActivity(intent);

    }
}
