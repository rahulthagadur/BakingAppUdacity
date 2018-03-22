package com.example.thagadur.bakingappudacity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.thagadur.bakingappudacity.fragments.RecipeDetailsFragment;
import com.example.thagadur.bakingappudacity.fragments.RecipeStepExoplayerFragment;
import com.example.thagadur.bakingappudacity.fragments.RecipeStepviewFragment;
import com.example.thagadur.bakingappudacity.model.RecipeSteps;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsFragment.OnFragmentInteractionListener {

    protected String mRecipeName;
    private ArrayList<RecipeSteps> mRecipeSteps = new ArrayList<>();
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detailsteps);

        Intent intent = getIntent();
        if (intent.hasExtra(getString(R.string.key_arrayrecipesteps)) && intent.hasExtra(getString(R.string.key_recipename))) {
            mRecipeSteps = intent.getParcelableArrayListExtra(getString(R.string.key_arrayrecipesteps));
            mRecipeName = intent.getStringExtra(getString(R.string.key_recipename));
            setTitle(mRecipeName);
        }

        if (findViewById(R.id.steps_instruction) != null) {
            isTwoPane = true;

            if (savedInstanceState == null) {

                RecipeStepviewFragment recipeStepviewFragment = new RecipeStepviewFragment();
                recipeStepviewFragment.setRecipeSteps(mRecipeSteps);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.steps_exoplayer_instruction_container, recipeStepviewFragment)
                        .commit();


                RecipeStepExoplayerFragment recipeStepExoplayerFragment = new RecipeStepExoplayerFragment();
                recipeStepExoplayerFragment.setRecipeSteps(mRecipeSteps);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.steps_exoplayer_container, recipeStepExoplayerFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(int position) {

        if (isTwoPane) {
            RecipeStepviewFragment newRecipeStepviewFragment = new RecipeStepviewFragment();
            newRecipeStepviewFragment.setRecipeSteps(mRecipeSteps);
            newRecipeStepviewFragment.setcurrentpostionIndex(position);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.steps_exoplayer_instruction_container, newRecipeStepviewFragment)
                    .commit();


            RecipeStepExoplayerFragment newRecipeStepExoplayerFragment = new RecipeStepExoplayerFragment();
            newRecipeStepExoplayerFragment.setRecipeSteps(mRecipeSteps);
            newRecipeStepExoplayerFragment.setcurrentpostionIndex(position);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.steps_exoplayer_container, newRecipeStepExoplayerFragment)
                    .commit();

        } else {
            Intent intent = new Intent(this, RecipeDetailStepsActivity.class);
            intent.putExtra(getString(R.string.key_arrayrecipesteps), mRecipeSteps);
            intent.putExtra(getString(R.string.key_steps_postion), position);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
