package com.github.wellwineo.bmi_calculator.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.github.wellwineo.bmi_calculator.R;

public class ResultsActivity extends AppCompatActivity {

    Button button;
    TextView title;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        button = findViewById(R.id.backButton);
        title = findViewById(R.id.title);
        result = findViewById(R.id.result);


        button.setOnClickListener(view -> finish());

        String titleText = "No title";
        String resultText = "No recommendations";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            titleText = bundle.getString("title");
            resultText = bundle.getString("result");
        }

        title.setText(titleText);
        result.setText(resultText);
    }
}