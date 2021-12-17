package com.github.wellwineo.bmi_calculator.Calculator;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.wellwineo.bmi_calculator.R;

import java.util.HashMap;

/**
 * Fragment to calculate Body Mass Index
 */
public class BMI extends Fragment {

    Button btn;
    EditText etWeight, etHeight;
    Resources resources;

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
        resources = getResources();

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

        Double index = weight / Math.pow(height, 2);

        if (index.isNaN() || index.isInfinite()){
            Toast.makeText(getContext(), "Некорректные данные",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getContext(), ResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", resources.getString(R.string.bmi_calculator));
        bundle.putString("result", getRecommends(index));
        bundle.putString("result_verb", getVerbosityRecommends());
        bundle.putDouble("index", index);
        bundle.putBoolean("is_ok", 18.5 < index && index < 30);
        bundle.putSerializable("values", getInputData());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private String getRecommends(double index){
        if (index < 18.5)
            return resources.getString(R.string.bmi_recommends_deficit);
        else if (18.5 <= index && index < 25)
            return resources.getString(R.string.bmi_recommends_norm);
        else if (25 <= index && index < 30)
            return resources.getString(R.string.bmi_recommends_proficit);
        else if (30 <= index && index < 35)
            return resources.getString(R.string.bmi_recommends_1_stage);
        else if (35 <= index && index < 40)
            return resources.getString(R.string.bmi_recommends_2_stage);
        else
            return resources.getString(R.string.bmi_recommends_3_stage);
    }

    private String getVerbosityRecommends(){
        return resources.getString(R.string.bmi_recommends_methodic);
    }

    private HashMap<String, String> getInputData(){
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