package com.example.android.logintask.LogIn;

import com.google.gson.annotations.SerializedName;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacebookLoginResponse {
    private String email;
    private String name;
    private String gender;
    private CircleImageView imgId;
    @SerializedName("security_token")
    private String securityToken;


    public FacebookLoginResponse(String email, String name, String gender, String securityToken, CircleImageView imgId) {
        this.email = email;
        this.imgId = imgId;
        this.name = name;
        this.gender = gender;
        this.securityToken = securityToken;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public CircleImageView getImgId() {
        return imgId;
    }

}
