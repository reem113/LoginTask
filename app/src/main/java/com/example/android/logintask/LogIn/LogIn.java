package com.example.android.logintask.LogIn;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.logintask.DataView.DataView;
import com.example.android.logintask.R;
import com.example.android.logintask.databinding.DataViewFragmentBinding;
import com.example.android.logintask.databinding.LogInFragmentBinding;


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.concurrent.Executor;

import retrofit2.Retrofit;


public class LogIn extends Fragment {

    private LogInViewModel mViewModel;
    LogInFragmentBinding logInFragmentBinding;
    CallbackManager callbackManager;
    AccessTokenTracker token;
    String email;
    String password;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        logInFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.log_in_fragment,container,false);
        mViewModel = new ViewModelProvider(this).get(LogInViewModel.class);
        mAuth = FirebaseAuth.getInstance();
        loginFacebok();

        logInFragmentBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email =logInFragmentBinding.email.getText().toString().trim();
                password = logInFragmentBinding.password.getText().toString().trim();
                userLogin(email,password);

                          }
        });
        logInFragmentBinding.forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(logInFragmentBinding.loginView).navigate(R.id.action_logIn_to_resetPassword);
            }
        });
        logInFragmentBinding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(logInFragmentBinding.loginView).navigate(R.id.action_logIn_to_signUp);
            }
        });

        return logInFragmentBinding.getRoot();
    }
    //handling login to facebook
    private void loginFacebok() {
        callbackManager = CallbackManager.Factory.create();
        logInFragmentBinding.fbloginButton.setReadPermissions("email","public_profile");
        logInFragmentBinding.fbloginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("FB Success", "facebook:onSuccess:" + loginResult);
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "On Cancel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("handle token", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("sign in success","signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            DataView.updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("sign in failure", "signInWithCredential:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            DataView.updateUI(null);
                        }

                    }
                });
    }

    //handling user login through his email and pw
    private void userLogin(String email, String password) {
        mViewModel.userLogin(email, password).observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                Log.i("login","on changed" + loginResponse);
                if(loginResponse != null){
                    if(loginResponse.getSuccess().equals("true")){
                        Toast.makeText(getContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(logInFragmentBinding.loginView).navigate(R.id.action_logIn_to_dataView);

                    }else {
                        Toast.makeText(getContext(),"error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LogInViewModel.class);
        // TODO: Use the ViewModel
    }

}