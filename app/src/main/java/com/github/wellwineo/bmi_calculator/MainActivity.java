package com.github.wellwineo.bmi_calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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