package com.github.wellwineo.bmi_calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.github.wellwineo.bmi_calculator.ormliteDB.DatabaseManager;
import com.github.wellwineo.bmi_calculator.ormliteDB.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ResultsFragment extends Fragment {

    ArrayList<Result> results;
    ListView lvResults;
    ResultListViewAdapter adapter;
    Button btn;

    public ResultsFragment() { }

    public static ResultsFragment newInstance() {
        return new ResultsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DatabaseManager dbManager = DatabaseManager.getInstance(getContext());
        results = (ArrayList<Result>) dbManager.getAllResults();
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter = new ResultListViewAdapter(getContext(), results);
        lvResults.setAdapter(adapter);
        btn.setOnClickListener(this::clearHandler);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvResults = getView().findViewById(R.id.lvResults);
        btn = view.findViewById(R.id.clearResultsButton);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.results_fragment, container, false);
    }

    private void clearHandler(View view){
        DatabaseManager dbManager = DatabaseManager.getInstance(getContext());
        dbManager.clearAllData();
    }
}