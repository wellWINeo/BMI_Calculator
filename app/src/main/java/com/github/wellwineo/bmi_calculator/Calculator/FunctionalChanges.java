package com.github.wellwineo.bmi_calculator.Calculator;

import android.content.Intent;
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
import java.util.Map;

public class FunctionalChanges extends Fragment {

    // param names
    private static final String SEX_PARAM = "sex";
    private static final String AGE_PARAM = "age";

    // params
    private Sex sex;
    private int age;

    // elements
    EditText etHeartRate, etSystolicPressure, etDiastolicPressure, etAge,
            etWeight, etHeight;
    Button btn;

    public FunctionalChanges() { }

    public static FunctionalChanges newInstance(Sex sex, int age) {
        FunctionalChanges fragment = new FunctionalChanges();
        Bundle args = new Bundle();
        args.putString(SEX_PARAM, sex.toString());
        args.putString(AGE_PARAM, String.valueOf(age));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null){
            sex = Sex.valueOf(args.getString(SEX_PARAM));
            age = args.getInt(AGE_PARAM);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etHeartRate = view.findViewById(R.id.heartRate);
        etSystolicPressure = view.findViewById(R.id.systolicPressure);
        etDiastolicPressure = view.findViewById(R.id.diastolicPressure);
        etAge = view.findViewById(R.id.age);
        etWeight = view.findViewById(R.id.weight);
        etHeight = view.findViewById(R.id.height);
        btn = view.findViewById(R.id.calculateButton);

        btn.setOnClickListener(this::buttonHandler);
    }

    private void buttonHandler(View view) {
        double heartrate = 0, systolicPressure = 0, diastolicPressure = 0;
        double age = 0, weight = 0, height = 0;
        try {
            heartrate = Double.parseDouble(String.valueOf(
                    etHeartRate.getText()));
            systolicPressure = Double.parseDouble(String.valueOf(
                    etSystolicPressure.getText()));
            diastolicPressure = Double.parseDouble(String.valueOf(
                    etDiastolicPressure.getText()));
            age = Double.parseDouble(String.valueOf(etAge.getText()));
            weight = Double.parseDouble(String.valueOf(etWeight.getText()));
            height = Double.parseDouble(String.valueOf(etHeight.getText()));
        } catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Invalid input data",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double functionalIndex = 0.011 * heartrate + 0.014 * systolicPressure +
                0.008 * diastolicPressure + 0.014 * age + 0.009 * weight +
                0.009 * height - 0.27;

        Intent intent = new Intent(getContext(), ResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", "Коэффицент выносливости");
        bundle.putString("result", String.valueOf(functionalIndex));
        bundle.putSerializable("values", getInputData());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private HashMap<String, String> getInputData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("heartrate", etHeartRate.getText().toString());
        map.put("systolicPressure", etSystolicPressure.getText().toString());
        map.put("diastolicPressure", etDiastolicPressure.getText().toString());
        map.put("age", etAge.getText().toString());
        map.put("weight", etWeight.getText().toString());
        map.put("height", etHeight.getText().toString());
        return map;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_functional_changes, container, false);
    }
}