package com.github.wellwineo.bmi_calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    ListAdapter adapter;

    public ResultsFragment() { }

    public static ResultsFragment newInstance() {
        return new ResultsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // DEBUG
//        results = new ArrayList<>(Arrays.asList(
//                new Result(1, "Индекс массы тела", "Все хорошо", true, new HashMap<>()),
//                new Result(2, "Индекс массы тела", "Все хорошо", true, new HashMap<>()),
//                new Result(3, "Индекс массы тела", "Все хорошо", true, new HashMap<>())
//        ));

        DatabaseManager dbManager = DatabaseManager.getInstance(getContext());
        results = (ArrayList<Result>) dbManager.getAllResults();
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter = new ResultListViewAdapter(getContext(), results);
        lvResults.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvResults = getView().findViewById(R.id.lvResults);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.results_fragment, container, false);
    }
}