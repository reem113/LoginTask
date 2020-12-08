package com.example.android.logintask.uimain;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.drm.DrmStore;
import android.os.Bundle;
import android.provider.Settings;

import com.example.android.logintask.R;
import com.example.android.logintask.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    ActivityMainBinding activityMainBinding;
    public static final String SOUND_EFFECTS_ENABLED = "sound_effects_enabled";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);




    }
}