package com.github.wellwineo.bmi_calculator.Calculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.wellwineo.bmi_calculator.R;

import java.util.HashMap;


public class MotorAbility extends Fragment {

    private static final String SEX_PARAM = "sex";
    public static final String AGE_PARAM = "age";

    private Sex sex;
    private int age;

    EditText etStepsCount;
    Button btn;

    public MotorAbility() { }

    public static MotorAbility newInstance(Sex sex, int age) {
        MotorAbility fragment = new MotorAbility();
        Bundle args = new Bundle();
        args.putString(SEX_PARAM, sex.toString());
        args.putInt(AGE_PARAM, age);
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
            Toast.makeText(getContext(), sex.toString() + " " + age, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etStepsCount = view.findViewById(R.id.stepsCount);
        btn = view.findViewById(R.id.calculateButton);

        btn.setOnClickListener(this::buttonHandler);
    }

    private void buttonHandler(View view){
        int stepsCount = 0;
        try {
            stepsCount = Integer.parseInt(String.valueOf(etStepsCount.getText()));
        } catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Invalid value",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getContext(), ResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", "Уровень двигательной активности");
        bundle.putString("result", String.valueOf(stepsCount));
        bundle.putSerializable("values", getInputData());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private HashMap<String, String> getInputData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("stepsCount", etStepsCount.getText().toString());
        return map;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motor_ability, container, false);
    }
}