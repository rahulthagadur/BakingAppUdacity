package com.example.thagadur.bakingappudacity.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class RecipeSteps implements Parcelable {


    public static final Creator<RecipeSteps> CREATOR = new Creator<RecipeSteps>() {
        @Override
        public RecipeSteps createFromParcel(Parcel in) {
            return new RecipeSteps(in);
        }

        @Override
        public RecipeSteps[] newArray(int size) {
            return new RecipeSteps[size];
        }
    };
    @SerializedName("id")
    private int id;
    @SerializedName("shortDescription")
    private String mShortDescription;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("videoURL")
    private String mVideoUrl;
    @SerializedName("thumbnailURL")
    private String mThumnailUrl;


    protected RecipeSteps(Parcel in) {
        id = in.readInt();
        mShortDescription = in.readString();
        mDescription = in.readString();
        mVideoUrl = in.readString();
        mThumnailUrl = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public void setmShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmVideoUrl() {
        return mVideoUrl;
    }

    public void setmVideoUrl(String mVideoUrl) {
        this.mVideoUrl = mVideoUrl;
    }

    public String getmThumnailUrl() {
        return mThumnailUrl;
    }

    public void setmThumnailUrl(String mThumnailUrl) {
        this.mThumnailUrl = mThumnailUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(mShortDescription);
        dest.writeString(mDescription);
        dest.writeString(mVideoUrl);
        dest.writeString(mThumnailUrl);
    }
}
