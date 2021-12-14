package com.github.wellwineo.bmi_calculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.wellwineo.bmi_calculator.Calculator.Sex;

public class CalculatorsListFragment extends Fragment {

    LinearLayout llButtons;
    EditText etAge;
    RadioGroup rgSexGroup;
    RadioButton rbMaleButton;
    RadioButton rbFemaleButton;
    Context context;

    public CalculatorsListFragment() { }

    public static CalculatorsListFragment newInstance() {
        return new CalculatorsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        fillListViewButtons();
    }


    private void fillListViewButtons(){
        llButtons.removeAllViews();

        String[] categories = getResources().getStringArray(R.array.categories);

        for(int i = 0; i < categories.length; i++){
            Button btn = new Button(context);
            btn.setText(categories[i]);
            int finalI = i;
            btn.setOnClickListener(view -> {
                // read sex info from radio group
                Sex sex;
                if (rbMaleButton.isChecked())
                    sex = Sex.MALE;
                else if (rbFemaleButton.isChecked())
                    sex = Sex.FEMALE;
                else {
                    Toast.makeText(context, "пожалуйста, укажите ваш пол",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = 0;
                try {
                    age = Integer.parseInt(String.valueOf(etAge.getText()));
                } catch (NumberFormatException e){
                    e.printStackTrace();
                    Toast.makeText(context, "пожалуйста, укажите ваш возраст",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(context, CalculatorViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("index", finalI);
                bundle.putInt("age", age);
                bundle.putString("sex", sex.toString());
                intent.putExtras(bundle);
                startActivity(intent);
            });

            llButtons.addView(btn);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llButtons = view.findViewById(R.id.llButtons);
        etAge = view.findViewById(R.id.etAge);
        rbMaleButton = view.findViewById(R.id.radio_male);
        rbFemaleButton = view.findViewById(R.id.radio_female);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calculators_list_fragment, container, false);
    }
}