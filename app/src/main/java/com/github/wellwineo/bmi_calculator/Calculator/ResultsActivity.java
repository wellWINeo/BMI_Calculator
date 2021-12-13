package com.github.wellwineo.bmi_calculator.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.github.wellwineo.bmi_calculator.R;

public class ResultsActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        button = findViewById(R.id.backButton);
        textView = findViewById(R.id.title);

        button.setOnClickListener(view -> finish());

        String title = "No title";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            title = bundle.getString("title");

        textView.setText(title);
    }
}