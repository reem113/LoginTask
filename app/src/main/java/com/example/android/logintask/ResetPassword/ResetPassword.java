package com.example.android.logintask.ResetPassword;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.logintask.R;
import com.example.android.logintask.databinding.ResetPasswordFragmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends Fragment {

    private ResetPasswordViewModel mViewModel;
    ResetPasswordFragmentBinding resetPasswordFragmentBinding;
    FirebaseAuth auth;
    String email;
    String exceptionMsg;

    public static ResetPassword newInstance() {
        return new ResetPassword();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        resetPasswordFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.reset_password_fragment,container,false);
        auth =FirebaseAuth.getInstance();
        resetPasswordFragmentBinding.sendResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = resetPasswordFragmentBinding.emailEditText.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getActivity(), "please enter your email first", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "please check your email", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(resetPasswordFragmentBinding.view).navigate(R.id.action_resetPassword_to_logIn);
                            }
                            else {
                                exceptionMsg = task.getException().getMessage();
                                Toast.makeText(getActivity(), "error occured"+ exceptionMsg, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        return resetPasswordFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        // TODO: Use the ViewModel
    }

}