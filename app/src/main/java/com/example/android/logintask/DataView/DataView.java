package com.example.android.logintask.DataView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.logintask.R;
import com.example.android.logintask.databinding.DataViewFragmentBinding;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataView extends Fragment {

    private static FirebaseAuth mAuth;
    DataViewFragmentBinding dataViewFragmentBinding;
    private DataViewViewModel mViewModel;
    CircleImageView circleImageView;

    public static DataView newInstance() {
        return new DataView();
    }

    //getting profile info from fb login ad storing it into var
    public static void updateUI(FirebaseUser user) {
        user = mAuth.getCurrentUser();
        if (user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            boolean emailVerified = user.isEmailVerified();
            String uid = user.getUid();
        }else{}

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dataViewFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.data_view_fragment,container,false);
        return dataViewFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DataViewViewModel.class);
        // TODO: Use the ViewModel
    }

}