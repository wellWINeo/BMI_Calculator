package com.github.wellwineo.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class CalculateActivity extends AppCompatActivity {

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            index = bundle.getInt("index");

        init();
    }

    private void init(){
        loadFragment(index);

    }

    private void loadFragment(int idx){
        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentById(
//                CalculatorsList.buttons[idx].getFragmentId()
//        );
//        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_result);

        FragmentTransaction ft = fragmentManager.beginTransaction();
//        assert fragment != null;
        ft.replace(R.id.calculateFragment, new ResultsFragment());
        ft.commit();
    }

}