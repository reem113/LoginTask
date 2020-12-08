package com.example.android.logintask.SignUp;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


public class SignUpViewModel extends ViewModel {

    private SignUpRepository signUpRepository;

    public SignUpViewModel() {
        signUpRepository = new SignUpRepository();
    }
    public LiveData<SignUpResponse> signUpResponseLiveData(int photo, String firstName, String lastName, String gender,
                                                           String jobTitle, String email, String password){
        // pass the data from repo to viewModel to be passed to the main fragment
        return signUpRepository.userSignUp(photo, firstName, lastName, gender, jobTitle, email, password);
    }
}
