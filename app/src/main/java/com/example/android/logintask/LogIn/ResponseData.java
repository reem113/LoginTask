package com.example.android.logintask.LogIn;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResponseData implements Parcelable {

    @SerializedName("_id")
    private int id;
    @SerializedName("email")
    private String email;
    private String username;


    protected ResponseData(Parcel in) {
        id = in.readInt();
        username = in.readString();
        email = in.readString();
    }

    public static final Creator<ResponseData> CREATOR = new Creator<ResponseData>() {
        @Override
        public ResponseData createFromParcel(Parcel in) {
            return new ResponseData(in);
        }

        @Override
        public ResponseData[] newArray(int size) {
            return new ResponseData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() { return 0;}

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(email);
        parcel.writeString(username);
        parcel.writeInt(id);
    }
}
