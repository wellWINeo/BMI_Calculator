package com.github.wellwineo.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Startup extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        btn = findViewById(R.id.btnStartupContinue);

        btn.setOnClickListener(view -> {
            finish();
        });
    }
}