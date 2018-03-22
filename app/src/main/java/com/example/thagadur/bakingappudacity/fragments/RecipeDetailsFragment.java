package com.example.thagadur.bakingappudacity.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thagadur.bakingappudacity.Adapter.IngredientsRecipeAdapter;
import com.example.thagadur.bakingappudacity.Adapter.RecipeStepsAdapter;
import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.callback.RecipeStepsCallback;
import com.example.thagadur.bakingappudacity.model.Ingrediants;
import com.example.thagadur.bakingappudacity.model.RecipeSteps;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDetailsFragment extends Fragment implements RecipeStepsCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected ArrayList<Ingrediants> ingrediants;
    protected ArrayList<RecipeSteps> recipeSteps;
    protected RecyclerView mRecyclerView, mReCyclerviewRecipe;
    protected IngredientsRecipeAdapter mAdapter;
    protected RecipeStepsAdapter recipeStepsAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private View mRootView;

    private Context mContext;

    public RecipeDetailsFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static RecipeDetailsFragment newInstance(String param1, String param2) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_detailrecipe, container, false);
        initObjects();
        return mRootView;
    }

    private void initObjects() {
        mContext = getActivity();
        mRecyclerView = mRootView.findViewById(R.id.recyclerview1);
        mReCyclerviewRecipe = mRootView.findViewById(R.id.rcv_steps);
        ingrediants = new ArrayList<>();
        recipeSteps = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(getString(R.string.key_arrayingredients))) {
            ingrediants = intent.getParcelableArrayListExtra(getString(R.string.key_arrayingredients));


        }

        Log.e("fhdkfdkfg", "" + ingrediants.size());
        mAdapter = new IngredientsRecipeAdapter(ingrediants, mContext);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager1);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);

        if (intent.hasExtra(getString(R.string.key_arrayrecipesteps))) {
            recipeSteps = intent.getParcelableArrayListExtra(getString(R.string.key_arrayrecipesteps));
        }
        recipeStepsAdapter = new RecipeStepsAdapter(mContext, recipeSteps, this);
        mReCyclerviewRecipe.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mContext);
        mReCyclerviewRecipe.setLayoutManager(layoutManager2);
        mReCyclerviewRecipe.setNestedScrollingEnabled(false);
        mReCyclerviewRecipe.setAdapter(recipeStepsAdapter);

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement RecyclerviewClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onServiceCategoryClick(int position, boolean twopane) {
        mListener.onClick(position);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void onClick(int position);
    }
}
