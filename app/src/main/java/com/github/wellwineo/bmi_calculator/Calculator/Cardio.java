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


public class Cardio extends Fragment {

    // param names
    private static final String SEX_PARAM = "sex";
    private static final String AGE_PARAM = "age";

    // params
    private Sex sex;
    private int age;

    // elements
    EditText etHeartRate, etSystolicPressure;
    Button btn;

    public Cardio() {
        // Required empty public constructor
    }

    public static Cardio newInstance(Sex sex, int age) {
        Cardio fragment = new Cardio();
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
        btn = view.findViewById(R.id.calculateButton);

        btn.setOnClickListener(this::buttonHandler);
    }

    private void buttonHandler(View view) {
        int heartrate, systolicPressure;
        try {
            heartrate = Integer.parseInt(String.valueOf(etHeartRate.getText()));
            systolicPressure = Integer.parseInt(String.valueOf(
                    etSystolicPressure.getText()));
        } catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Invalid input data",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double cardioIndex = (heartrate * systolicPressure) / 100f;
        Resources resources = getResources();

        Intent intent = new Intent(getContext(), ResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", resources.getString(R.string.Cardio_title));

        if (cardioIndex <= 74)
            bundle.putString("result", resources.getString(R.string.cardio_lt_74));
        else if (75 <= cardioIndex && cardioIndex <= 80)
            bundle.putString("result", resources.getString(R.string.cardio_75_80));
        else if (81 <= cardioIndex && cardioIndex <= 90)
            bundle.putString("result", resources.getString(R.string.cardio_91_100));
        else if (91 <= cardioIndex && cardioIndex <= 100)
            bundle.putString("result", resources.getString(R.string.cardio_91_100));
        else if (101 <= cardioIndex)
            bundle.putString("result", resources.getString(R.string.cardio_gt_101));

        bundle.putString("result_verb", resources.getString(R.string.cardio_methodic));
        bundle.putSerializable("values", getInputData());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private HashMap<String, String> getInputData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("heartrate", etHeartRate.getText().toString());
        map.put("systolicPressure", etSystolicPressure.getText().toString());
        return map;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cardio, container, false);
    }
}