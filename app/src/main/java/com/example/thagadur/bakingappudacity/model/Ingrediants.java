package com.example.thagadur.bakingappudacity.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Ingrediants implements Parcelable {

    public static final Creator<Ingrediants> CREATOR = new Creator<Ingrediants>() {
        @Override
        public Ingrediants createFromParcel(Parcel in) {
            return new Ingrediants(in);
        }

        @Override
        public Ingrediants[] newArray(int size) {
            return new Ingrediants[size];
        }
    };
    @SerializedName("quantity")
    private String mQuantity;
    @SerializedName("measure")
    private String mMeasure;
    @SerializedName("ingredient")
    private String mIngredient;


    public Ingrediants(String quantity, String measure, String ingredient) {
        this.mQuantity = quantity;
        this.mMeasure = measure;
        this.mIngredient = ingredient;
    }

    public Ingrediants(Parcel in) {
        mQuantity = in.readString();
        mMeasure = in.readString();
        mIngredient = in.readString();
    }


    public Ingrediants(String Quantity, String Measure) {
        mQuantity = Quantity;
        mMeasure = Measure;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mQuantity);
        parcel.writeString(mMeasure);
        parcel.writeString(mIngredient);
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmMeasure() {
        return mMeasure;
    }

    public void setmMeasure(String mMeasure) {
        this.mMeasure = mMeasure;
    }

    public String getmIngredient() {
        return mIngredient;
    }

    public void setmIngredient(String mIngredient) {
        this.mIngredient = mIngredient;
    }
}
