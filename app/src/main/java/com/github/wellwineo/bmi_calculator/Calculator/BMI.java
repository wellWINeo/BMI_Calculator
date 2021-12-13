package com.github.wellwineo.bmi_calculator.Calculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.wellwineo.bmi_calculator.R;

import java.util.HashMap;

/**
 * Fragment to calculate Body Mass Index
 */
public class BMI extends Fragment {

    Button btn;
    EditText etWeight, etHeight;

    public BMI() { }

    public static BMI newInstance() {
        return new BMI();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = view.findViewById(R.id.calculateButton);

        etWeight = view.findViewById(R.id.weight);
        etHeight = view.findViewById(R.id.height);

        btn.setOnClickListener(this::buttonHandler);
    }

    private void buttonHandler(View view){
        double weight, height;
        try {
            weight = Double.parseDouble(String.valueOf(etWeight.getText()));
            height = Double.parseDouble(String.valueOf(etHeight.getText())) / 100;
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Fields can't be empty or 0",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }

        double index = weight / Math.pow(height, 2);

        Intent intent = new Intent(getContext(), ResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", "Индекс массы тела");
        bundle.putString("result", String.valueOf(index));
        bundle.putSerializable("values", getInputData());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public HashMap<String, String> getInputData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("weight", etWeight.getText().toString());
        map.put("height", etHeight.getText().toString());
        return map;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_m_i, container, false);
    }
}