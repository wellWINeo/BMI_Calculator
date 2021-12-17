package com.github.wellwineo.bmi_calculator.Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.wellwineo.bmi_calculator.R;
import com.github.wellwineo.bmi_calculator.ormliteDB.DatabaseManager;
import com.github.wellwineo.bmi_calculator.ormliteDB.Result;

import java.util.HashMap;

public class ResultsActivity extends AppCompatActivity {

    private Button button;
    private TextView title;
    private TextView result;
    private TextView index;

    private String titleText = "No title";
    private String resultText = "No recommendations";
    private String resultVerbText = "No recommendations";
    private Double indexValue = 0.0;
    private Boolean isOK;
    private HashMap<String, String> map;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        button = findViewById(R.id.backButton);
        title = findViewById(R.id.title);
        result = findViewById(R.id.result);
        index = findViewById(R.id.index);
        button.setOnClickListener(view -> finish());
        result.setMovementMethod(new ScrollingMovementMethod());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            indexValue = bundle.getDouble("index");
            isOK = bundle.getBoolean("is_ok");
            titleText = bundle.getString("title");
            resultText = bundle.getString("result");
            resultVerbText = bundle.getString("result_verb");
            map = (HashMap<String, String>) bundle.getSerializable("values");
        }

        getSupportActionBar().setTitle(titleText);
        title.setText(resultText);
        result.setText(resultVerbText);
        index.setText(String.format("Ваш результат: %.2f", indexValue));

        if (isOK)
            index.setTextColor(Color.GREEN);
        else
            index.setTextColor(Color.RED);

        saveToDB();
    }

    private void saveToDB(){
        DatabaseManager dbManager = DatabaseManager.getInstance(getApplicationContext());
        Result result = new Result(titleText, resultText, isOK, map);
        if (dbManager.addResult(result) != 0){
            Toast.makeText(getApplicationContext(),
                    "Something went wrong, can't save result",
                    Toast.LENGTH_SHORT).show();
        }
    }
}