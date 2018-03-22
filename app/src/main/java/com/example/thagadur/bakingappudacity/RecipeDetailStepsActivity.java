package com.example.thagadur.bakingappudacity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.thagadur.bakingappudacity.fragments.RecipeStepExoplayerFragment;
import com.example.thagadur.bakingappudacity.fragments.RecipeStepviewFragment;
import com.example.thagadur.bakingappudacity.model.RecipeSteps;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RecipeDetailStepsActivity extends AppCompatActivity {

    public Button mButtonPrevious;
    public Button mButtonNext;
    private ArrayList<RecipeSteps> mRecipeSteps;
    private int currentpostionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail_view);
        mButtonPrevious = findViewById(R.id.btn_backwards);
        mButtonNext = findViewById(R.id.btn_forward);

        Intent intent = getIntent();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT || orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mButtonPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRecipeSteps != null) {
                        if (currentpostionIndex > 0) {
                            currentpostionIndex--;
                        } else {
                            currentpostionIndex = mRecipeSteps.size() - 1;
                        }
                        RecipeStepviewFragment recipeStepviewFragment = new RecipeStepviewFragment();
                        recipeStepviewFragment.setRecipeSteps(mRecipeSteps);
                        recipeStepviewFragment.setcurrentpostionIndex(currentpostionIndex);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.steps_exoplayer_instruction_container, recipeStepviewFragment)
                                .commit();

                        RecipeStepExoplayerFragment recipeStepExoplayerFragment = new RecipeStepExoplayerFragment();
                        recipeStepExoplayerFragment.setRecipeSteps(mRecipeSteps);
                        recipeStepExoplayerFragment.setcurrentpostionIndex(currentpostionIndex);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.steps_exoplayer_container, recipeStepExoplayerFragment)
                                .commit();

                    }
                }
            });


            mButtonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRecipeSteps != null) {

                        if (currentpostionIndex < mRecipeSteps.size() - 1) {
                            currentpostionIndex++;
                        } else {
                            currentpostionIndex = 0;
                        }

                        RecipeStepviewFragment recipeStepviewFragment = new RecipeStepviewFragment();
                        recipeStepviewFragment.setRecipeSteps(mRecipeSteps);
                        recipeStepviewFragment.setcurrentpostionIndex(currentpostionIndex);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.steps_exoplayer_instruction_container, recipeStepviewFragment)
                                .commit();


                        RecipeStepExoplayerFragment recipeStepExoplayerFragment = new RecipeStepExoplayerFragment();
                        recipeStepExoplayerFragment.setRecipeSteps(mRecipeSteps);
                        recipeStepExoplayerFragment.setcurrentpostionIndex(currentpostionIndex);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.steps_exoplayer_container, recipeStepExoplayerFragment)
                                .commit();

                    }

                }
            });
        }

        if (intent.hasExtra(getString(R.string.key_arrayrecipesteps)) &&
                (intent.hasExtra(getString(R.string.key_steps_postion)))) {
            mRecipeSteps = intent.getParcelableArrayListExtra(getString(R.string.key_arrayrecipesteps));

            Log.e("mRecipeSteps", "" + mRecipeSteps.size());
            currentpostionIndex = intent.getIntExtra(getString(R.string.key_steps_postion), 0);
        }

        if (savedInstanceState == null) {

            RecipeStepviewFragment recipeStepviewFragment = new RecipeStepviewFragment();
            recipeStepviewFragment.setRecipeSteps(mRecipeSteps);
            recipeStepviewFragment.setcurrentpostionIndex(currentpostionIndex);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.steps_exoplayer_instruction_container, recipeStepviewFragment)
                    .commit();


            RecipeStepExoplayerFragment recipeStepExoplayerFragment = new RecipeStepExoplayerFragment();
            recipeStepExoplayerFragment.setRecipeSteps(mRecipeSteps);
            recipeStepExoplayerFragment.setcurrentpostionIndex(currentpostionIndex);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.steps_exoplayer_container, recipeStepExoplayerFragment)
                    .commit();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
