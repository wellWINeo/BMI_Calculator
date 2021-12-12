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


    private void fillListViewButtons(){
        llButtons.removeAllViews();

        String[] categories = getResources().getStringArray(R.array.categories);

        for(int i = 0; i < categories.length; i++){
            Button btn = new Button(context);
            btn.setText(categories[i]);
            int finalI = i;
            btn.setOnClickListener(view -> {
                Intent intent = new Intent(context, CalculatorViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("index", finalI);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calculators_list_fragment, container, false);
    }
}