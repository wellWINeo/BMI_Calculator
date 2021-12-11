package com.github.wellwineo.bmi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navBar;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // some launch staff
        init();
        Objects.requireNonNull(getSupportActionBar()).hide();

        // TODO remove
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(
                getBaseContext());

        // launch startup activity (probably useless)
        Intent intent = new Intent(getApplicationContext(), Startup.class);
        startActivity(intent);

        boolean prevStarted = prefs.getBoolean("previously_started", false);
//        if (!prevStarted) {
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putBoolean("previously_started", true);
//            editor.apply();
//            // TODO launch startup activity
//        }
    }

    @SuppressLint("NonConstantResourceId")
    private void init(){

        // set fragment
        loadFragment(new HomeFragment());

        navBar = findViewById(R.id.bottomNavigationView);

        navBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_tab:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.calculate_tab:
                    loadFragment(new CalculatorsListFragment());
                    return true;
                case R.id.result_tab:
                    loadFragment(new ResultsFragment());
                    return true;
            }
            return false;
        });
    }


    private void loadFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

}