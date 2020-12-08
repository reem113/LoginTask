package com.example.android.logintask.LogIn;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable {
    @SerializedName("success")
    private String success;
    @SerializedName("data")
    private ResponseData responseData;

    protected LoginResponse(Parcel in) {
        success = in.readString();
        responseData = in.readParcelable(ResponseData.class.getClassLoader());
    }


    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
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
