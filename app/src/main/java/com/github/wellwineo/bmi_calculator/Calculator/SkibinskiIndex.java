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

public class SkibinskiIndex extends Fragment {

    // param names
    private static final String SEX_PARAM = "sex";
    private static final String AGE_PARAM = "age";

    // params
    private Sex sex;
    private int age;

    // elements
    EditText etLungsVolume, etHeartRate, etStange;
    Button btn;


    public SkibinskiIndex() { }


    public static SkibinskiIndex newInstance(Sex sex, int age) {
        SkibinskiIndex fragment = new SkibinskiIndex();
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
        etLungsVolume = view.findViewById(R.id.lungsVolume);
        etHeartRate = view.findViewById(R.id.heartRate);
        etStange = view.findViewById(R.id.stange);
        btn = view.findViewById(R.id.calculateButton);

        btn.setOnClickListener(this::buttonHandler);
    }

    private void buttonHandler(View view) {
        double lungsVolume = 0, heartrate = 0, stange = 0;
        try {
            lungsVolume = Double.parseDouble(String.valueOf(
                    etLungsVolume.getText()));
            heartrate = Double.parseDouble(String.valueOf(
                    etHeartRate.getText()));
            stange = Double.parseDouble(String.valueOf(etStange.getText()));
        } catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Invalid input data",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double skibinskiIndex = ((lungsVolume / 100) * stange) / heartrate;
        Resources resources = getResources();

        Intent intent = new Intent(getContext(), ResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", resources.getString(R.string.SkibinskiIndex_title));

        if (skibinskiIndex <= 5)
            bundle.putString("result", resources.getString(
                    R.string.skibinski_lt_5));
        else if (5 < skibinskiIndex && skibinskiIndex <= 10)
            bundle.putString("result", resources.getString(
                    R.string.skibinski_5_10));
        else if (10 < skibinskiIndex && skibinskiIndex <= 30)
            bundle.putString("result", resources.getString(
                    R.string.skibinski_10_30));
        else if (30 < skibinskiIndex && skibinskiIndex <= 60)
            bundle.putString("result", resources.getString(
                    R.string.skibinski_30_60));
        else if (60 < skibinskiIndex)
            bundle.putString("result", resources.getString(
                    R.string.skibinski_gt_60));

        bundle.putString("result_verb", resources.getString(R.string.skibinski_methodic));
        bundle.putSerializable("values", getInputData());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private HashMap<String, String> getInputData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("heartrate", etHeartRate.getText().toString());
        map.put("lungsVolume", etLungsVolume.getText().toString());
        map.put("stange", etStange.getText().toString());
        return map;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skibinski_index, container, false);
    }
}