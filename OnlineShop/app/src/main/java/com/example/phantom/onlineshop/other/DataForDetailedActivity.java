package com.example.phantom.onlineshop.other;

import android.os.Parcel;
import android.os.Parcelable;

public class DataForDetailedActivity implements Parcelable {
    public String keyName, keyWeight, keyPrice, keyImageUrl, keyDescription;

    protected DataForDetailedActivity(Parcel parcel) {
        keyName = parcel.readString();
        keyWeight = parcel.readString();
        keyPrice = parcel.readString();
        keyImageUrl = parcel.readString();
        keyDescription = parcel.readString();
    }

    public DataForDetailedActivity(String keyName, String keyWeight, String keyPrice, String keyImageUrl, String keyDescription) {
        this.keyName = keyName;
        this.keyWeight = keyWeight;
        this.keyPrice = keyPrice;
        this.keyImageUrl = keyImageUrl;
        this.keyDescription = keyDescription;
    }

    public static final Creator<DataForDetailedActivity> CREATOR = new Creator<DataForDetailedActivity>() {
        @Override
        public DataForDetailedActivity createFromParcel(Parcel in) {
            return new DataForDetailedActivity(in);
        }

        @Override
        public DataForDetailedActivity[] newArray(int size) {
            return new DataForDetailedActivity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(keyName);
        parcel.writeString(keyWeight);
        parcel.writeString(keyPrice);
        parcel.writeString(keyImageUrl);
        parcel.writeString(keyDescription);
    }
}
