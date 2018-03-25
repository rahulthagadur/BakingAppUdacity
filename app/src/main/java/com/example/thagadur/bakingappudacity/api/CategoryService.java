package com.example.thagadur.bakingappudacity.api;

import com.example.thagadur.bakingappudacity.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by thagadur on 03/03/2018.
 */

public interface CategoryService {
    @GET("59121517_baking/baking.json")
    Call<ArrayList<Category>> getRecipe();
}
