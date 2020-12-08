package com.example.android.logintask.SignUp;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.logintask.R;
import com.example.android.logintask.databinding.SignUpFragmentBinding;
import com.google.gson.annotations.SerializedName;

public class SignUp extends Fragment {

    private SignUpViewModel mViewModel;
    SignUpFragmentBinding signUpFragmentBinding;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int RESULT_OK = -1;
    private int id;
    private int image;
    private String firstName;
    private String lastName;
    private String gender;
    private String jobTitle;
    private String email;
    private String password;




    public static SignUp newInstance() {
        return new SignUp();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        signUpFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.sign_up_fragment,container,false);
        //to get data from user and pass it to the repo through user register method
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        signUpFragmentBinding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = signUpFragmentBinding.userImage.getId();
                firstName = signUpFragmentBinding.uFirstName.getText().toString().trim();
                lastName = signUpFragmentBinding.uLastName.getText().toString().trim();
                gender = signUpFragmentBinding.gender.getText().toString().trim();
                jobTitle = signUpFragmentBinding.jobTitle.getText().toString().trim();
                email = signUpFragmentBinding.email.getText().toString().trim();
                password = signUpFragmentBinding.password.getText().toString().trim();
                userRegister(image, firstName, lastName, gender, jobTitle, email, password);
            }
        });

        uploadPhoto();
        return signUpFragmentBinding.getRoot();
    }


    private void userRegister(int image, String firstName, String lastName, String gender, String jobTitle, String email, String password) {
        mViewModel.signUpResponseLiveData(image, firstName, lastName, gender, jobTitle, email, password).observe(this, new Observer<SignUpResponse>() {
            @Override
            public void onChanged(SignUpResponse signUpResponse) {
                Log.i("user sigup", "onChanged: " + signUpResponse);

                if (signUpResponse != null) {
                    if (signUpResponse.getSuccess().equals("true")) {
                        Toast.makeText(getContext(), "Sign up Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                    }

                    }
                }
            });
        }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        // TODO: Use the ViewModel
    }
        //uploading user profile image
    private void uploadPhoto() {
        signUpFragmentBinding.uploadTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                    if(ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        //permission not granted, request permission
                        String[] permissions ={ Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }
                    else{
                        //permission granted
                        pickImageFromGallery();
                    }
                }
                else{
                    //sys os less than marshmello
                    pickImageFromGallery();
                }
            }
        });
    }


    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else{
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK  && requestCode == IMAGE_PICK_CODE){
            signUpFragmentBinding.userImage.setImageURI(data.getData());
        }
    }



}
