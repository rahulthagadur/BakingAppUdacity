package com.example.thagadur.bakingappudacity.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Category implements Parcelable{


    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String mRecipeName;


    @SerializedName("ingredients")
    private ArrayList<Ingrediants> mIngredients = new ArrayList<>();
    @SerializedName("steps")
    private ArrayList<RecipeSteps> mSteps = new ArrayList<>();

    @SerializedName("servings")
    private String mServings;
    @SerializedName("image")
    private String mImage;


    protected Category(Parcel in) {
        id = in.readInt();
        mRecipeName = in.readString();
        mServings = in.readString();
        mImage = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(mRecipeName);
        parcel.writeString(mServings);
        parcel.writeString(mImage);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getmRecipeName() {
        return mRecipeName;
    }

    public void setmRecipeName(String mRecipeName) {
        this.mRecipeName = mRecipeName;
    }


    public ArrayList<Ingrediants> getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(ArrayList<Ingrediants> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public ArrayList<RecipeSteps> getmSteps() {
        return mSteps;
    }

    public void setmSteps(ArrayList<RecipeSteps> mSteps) {
        this.mSteps = mSteps;
    }


    public String getmServings() {
        return mServings;
    }

    public void setmServings(String mServings) {
        this.mServings = mServings;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }


}
