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
import android.widget.Toast;

import com.github.wellwineo.bmi_calculator.R;
import com.j256.ormlite.stmt.query.In;

public class LifeIndex extends Fragment {

    // param names
    private static final String SEX_PARAM = "sex";
    private static final String AGE_PARAM = "age";

    // params
    private Sex sex;
    private int age;

    // elements
    EditText etWeight, etLungsVolume;
    Button btn;

    public LifeIndex() { }

    public static LifeIndex newInstance(Sex sex, int age) {
        LifeIndex fragment = new LifeIndex();
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
        etWeight = view.findViewById(R.id.weight);
        etLungsVolume = view.findViewById(R.id.lungsVolume);
        btn = view.findViewById(R.id.calculateButton);

        btn.setOnClickListener(this::buttonHandler);
    }

    private void buttonHandler(View view) {
        double weight = 0;
        double lungsVolume = 0;
        try {
            weight = Double.parseDouble(String.valueOf(etWeight.getText()));
            lungsVolume = Double.parseDouble(String.valueOf(
                    etLungsVolume.getText()));
        } catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Invalid input data", Toast
                    .LENGTH_SHORT).show();
            return;
        }

        double lifeIndex = lungsVolume / weight;

        Intent intent = new Intent(getContext(), ResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", "Жизненный индекс");
        bundle.putString("result", String.valueOf(lifeIndex));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_index, container, false);
    }
}