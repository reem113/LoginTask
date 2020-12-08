package com.example.android.logintask.LogIn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class LogInViewModel extends ViewModel {
   LoginRepository loginRepository;

    public LogInViewModel(){loginRepository = new LoginRepository();}
    public LiveData<LoginResponse> userLogin (String email, String password){
        return loginRepository.userLogin(email, password);
    }
}