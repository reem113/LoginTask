package com.example.android.logintask.HomePage;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.logintask.R;
import com.example.android.logintask.databinding.HomePageFragmentBinding;

public class HomePage extends Fragment {

    private HomePageViewModel mViewModel;
    HomePageFragmentBinding homePageFragmentBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homePageFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.home_page_fragment, container, false);
        homePageFragmentBinding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(homePageFragmentBinding.homeView).navigate(R.id.action_homePage_to_logIn);
            }
        });
        homePageFragmentBinding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(homePageFragmentBinding.homeView).navigate(R.id.action_homePage_to_signUp);
            }
        });
        return homePageFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
        // TODO: Use the ViewModel
    }

}