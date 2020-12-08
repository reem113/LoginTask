package com.example.android.logintask.SignUp;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResponseData implements Parcelable {
    //defining the details of response data according to the name of the fields in json response
    @SerializedName("_id")
    private int id;
    @SerializedName("photo_id")
    private Uri photoId;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("job_title")
    private String jobTitle;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    protected ResponseData(Parcel in) {
        id = in.readInt();
        photoId = in.readParcelable(Uri.class.getClassLoader());
        firstName = in.readString();
        lastName = in.readString();
        gender = in.readString();
        jobTitle = in.readString();
        email = in.readString();
        password = in.readString();
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

    public Uri getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Uri photoId) {
        this.photoId = photoId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeParcelable(photoId, i);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(gender);
        parcel.writeString(jobTitle);
        parcel.writeString(email);
        parcel.writeString(password);
    }
}

