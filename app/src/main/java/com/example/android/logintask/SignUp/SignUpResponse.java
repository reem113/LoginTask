package com.example.android.logintask.SignUp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse implements Parcelable {

    @SerializedName("success")
    private String success;
    //data supposed that data contain all the response data
    @SerializedName("data")
    private ResponseData responseData;


    protected SignUpResponse(Parcel in) {
        success = in.readString();
        responseData = in.readParcelable(ResponseData.class.getClassLoader());
    }

    public static final Creator<SignUpResponse> CREATOR = new Creator<SignUpResponse>() {
        @Override
        public SignUpResponse createFromParcel(Parcel in) {
            return new SignUpResponse(in);
        }

        @Override
        public SignUpResponse[] newArray(int size) {
            return new SignUpResponse[size];
        }
    };

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(success);
        dest.writeParcelable(responseData, flags);
    }
    }

