package com.example.android.logintask.LogIn;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.android.logintask.Services.Api;
import com.example.android.logintask.Services.Interface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginRepository {
    private Interface apiInterface= Api.getInstance().apiInterface();
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData;
    public LiveData<LoginResponse> userLogin(String email,String password){
        loginResponseMutableLiveData = new MutableLiveData<>();
        apiInterface.userLogin(email,password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null && response.isSuccessful()  ) {
                    loginResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("error repo", "onFailure: "+t.getLocalizedMessage() );
                loginResponseMutableLiveData.setValue(null);
            }
        });
        return loginResponseMutableLiveData;
    }

}


