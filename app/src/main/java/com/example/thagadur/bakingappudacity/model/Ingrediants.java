package com.example.thagadur.bakingappudacity.model;

import android.os.Parcel;
import android.os.Parcelable;

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
    private String quantity;
    private String measure;
    private String ingredient;

    public Ingrediants(String quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Ingrediants(Parcel in) {
        quantity = in.readString();
        measure = in.readString();
        ingredient = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
