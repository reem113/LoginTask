package com.example.android.logintask.Services;

import android.net.Uri;

import com.example.android.logintask.LogIn.FacebookLoginResponse;
import com.example.android.logintask.LogIn.LoginResponse;
import com.example.android.logintask.LogIn.ResponseData;
import com.example.android.logintask.SignUp.SignUpResponse;
//import com.example.android.logintask.SignUp.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface Interface {
    @POST
    Call<FacebookLoginResponse>login(@Body FacebookLoginResponse user );

    @POST("path of the login in the api")
    Call<LoginResponse> userLogin (@Field("email") String email, @Field("password") String password);

    @POST("path of the sign up in the api")
    Call<SignUpResponse> userRegister (@Field("photo_id") int photo, @Field("first_name")String firstName, @Field("last_name")String lastName
    , @Field("gender")String gender, @Field("job_title")String jobTitle, @Field("email")String email, @Field("password")String password);
}
