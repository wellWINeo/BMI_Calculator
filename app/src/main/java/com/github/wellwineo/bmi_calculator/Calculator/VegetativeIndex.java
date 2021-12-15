package com.github.wellwineo.bmi_calculator.Calculator;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.wellwineo.bmi_calculator.R;

import java.util.HashMap;

public class VegetativeIndex extends Fragment {

    // param names
    private static final String SEX_PARAM = "sex";
    private static final String AGE_PARAM = "age";

    // params
    private Sex sex;
    private int age;

    // elements
    EditText etHeartRate, etDiastolicPressure;
    Button btn;


    public VegetativeIndex() { }

    public static VegetativeIndex newInstance(Sex sex, int age) {
        VegetativeIndex fragment = new VegetativeIndex();
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
        etDiastolicPressure = view.findViewById(R.id.diastolicPressure);
        btn = view.findViewById(R.id.calculateButton);

        btn.setOnClickListener(this::buttonHandler);
    }

    private void buttonHandler(View view) {
        double heartrate, diastolicPressure;
        try {
            heartrate = Double.parseDouble(String.valueOf(etHeartRate.getText()));
            diastolicPressure = Double.parseDouble(String.valueOf(
                    etDiastolicPressure.getText()));
        } catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Invalid input data",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double vegetativeIndex = 100 * (1 - diastolicPressure / heartrate);
        Resources resources = getResources();

        Intent intent = new Intent(getContext(), ResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", resources.getString(R.string.Vegetative_title));

        if (vegetativeIndex >= 31)
            bundle.putString("result", resources.getString(
                    R.string.vegetative_gt_31));
        else if (16 <= vegetativeIndex && vegetativeIndex <= 30)
            bundle.putString("result", resources.getString(
                    R.string.vegetative_16_30));
        else if (-15 <= vegetativeIndex && vegetativeIndex <= 15)
            bundle.putString("result", resources.getString(
                    R.string.vegetative_m15_m16));
        else if (-16 <= vegetativeIndex && vegetativeIndex <= 30)
            bundle.putString("result", resources.getString(
                    R.string.vegetative_m16_m30));
        else if (vegetativeIndex <= -30)
            bundle.putString("result", resources.getString(
                    R.string.vegetative_lt_m30));

        bundle.putString("result_verb", resources.getString(R.string.vegetative_methodic));
        bundle.putSerializable("values", getInputData());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private HashMap<String, String> getInputData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("heartrate", etHeartRate.getText().toString());
        map.put("diastolicPressure", etDiastolicPressure.getText().toString());
        return map;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vegetative_index, container, false);
    }
}