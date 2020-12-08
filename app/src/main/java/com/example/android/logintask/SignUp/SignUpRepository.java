package com.example.android.logintask.SignUp;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.android.logintask.Services.Api;
import com.example.android.logintask.LogIn.LoginResponse;
import com.example.android.logintask.Services.Interface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;


public class SignUpRepository {
    private Interface apiInterface = Api.getInstance().apiInterface();
    private MutableLiveData<SignUpResponse> signUpResponseMutableLiveData;

    //get response from api and store it in mutable live data
    public LiveData<SignUpResponse> userSignUp(int photo, String firstName, String lastName, String gender,
                                               String jobTitle, String email, String password) {
        signUpResponseMutableLiveData = new MutableLiveData<>();
        apiInterface.userRegister(photo, firstName, lastName, gender, jobTitle, email, password).enqueue(new Callback<SignUpResponse>() {

            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    signUpResponseMutableLiveData.setValue(response.body());
                }
            }


            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.e("error repo", "onFailure: " + t.getLocalizedMessage());
                signUpResponseMutableLiveData.setValue(null);
            }
        });
        return signUpResponseMutableLiveData;
    }
}

