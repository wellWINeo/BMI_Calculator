package com.github.wellwineo.bmi_calculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorsListFragment extends Fragment {

    LinearLayout llButtons;
    Context context;

    public CalculatorsListFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CalculatorsListFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    // wrapper class for button
    private static class customButton {
        private String text;
        private Class<?> cls;

        public customButton(String text, Class<?> cls){
            this.text = text;
            this.cls = cls;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Class<?> getCls() {
            return cls;
        }

        public void setCls(Class<?> cls) {
            this.cls = cls;
        }
    }

    private void fillListViewButtons(){
        // TODO fill with real data
        // calculator routing
        customButton[] buttons = new customButton[]{
                new customButton("Индекс массы тела", MainActivity.class),
                new customButton("test2", MainActivity.class),
                new customButton("test3", MainActivity.class),
                new customButton("test4", MainActivity.class),
                new customButton("test5", MainActivity.class),
                new customButton("test6", MainActivity.class),
                new customButton("test7", MainActivity.class),
                new customButton("test8", MainActivity.class),
                new customButton("test1", MainActivity.class),
                new customButton("test2", MainActivity.class),
                new customButton("test3", MainActivity.class),
                new customButton("test4", MainActivity.class),
                new customButton("test5", MainActivity.class),
                new customButton("test6", MainActivity.class),
                new customButton("test7", MainActivity.class),
                new customButton("test8", MainActivity.class)
        };

        for(customButton customBtn : buttons){
            Button btn = new Button(context);
            btn.setText(customBtn.text);
            btn.setOnClickListener(view -> {
                Intent intent = new Intent(context, customBtn.getCls());
                startActivity(intent);
            });

            llButtons.addView(btn);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llButtons = view.findViewById(R.id.llButtons);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calculators_list_fragment, container, false);
    }
}