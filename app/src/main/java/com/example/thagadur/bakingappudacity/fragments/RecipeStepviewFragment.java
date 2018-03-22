package com.example.thagadur.bakingappudacity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.model.RecipeSteps;

import java.util.ArrayList;

public class RecipeStepviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayList<RecipeSteps> recipeSteps;
    private int currentpostionIndex;


    private View mRootView;

    public RecipeStepviewFragment() {
        // Required empty public constructor
    }

    public static RecipeStepviewFragment newInstance(String param1, String param2) {
        RecipeStepviewFragment fragment = new RecipeStepviewFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_recipe_step_instruction, container, false);

        TextView steps = mRootView.findViewById(R.id.recipes_ins);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(getString(R.string.key_arrayrecipesteps)) &&
                    (savedInstanceState.containsKey(getString(R.string.key_steps_postion)))) {
                recipeSteps = savedInstanceState.getParcelableArrayList(getString(R.string.key_arrayrecipesteps));
                currentpostionIndex = savedInstanceState.getInt(getString(R.string.key_steps_postion));
            }

        }

        if (recipeSteps != null) {

            String recipeInstruction = recipeSteps.get(currentpostionIndex).getmDescription();
            steps.setText(recipeInstruction);
        }

        return mRootView;
    }

    public void setRecipeSteps(ArrayList<RecipeSteps> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public void setcurrentpostionIndex(int currentpostionIndex) {
        this.currentpostionIndex = currentpostionIndex;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.key_arrayrecipesteps), recipeSteps);
        outState.putInt(getString(R.string.key_steps_postion), currentpostionIndex);
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
    }
}
