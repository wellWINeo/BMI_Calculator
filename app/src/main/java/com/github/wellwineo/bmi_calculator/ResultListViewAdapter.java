package com.github.wellwineo.bmi_calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.wellwineo.bmi_calculator.ormliteDB.Result;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResultListViewAdapter implements ListAdapter {
    ArrayList<Result> results;
    Context context;

    public ResultListViewAdapter(Context context, ArrayList<Result> results){
        this.context = context;
        this.results = results;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) { }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) { }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int i) {
        return results.get(i);
    }

    @Override
    public long getItemId(int i) {
        return results.get(i).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Result result = results.get(i);
        if (view == null){
            // prevent null
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.result_item, null);
            view.setOnClickListener(view1 -> {
                Toast.makeText(context, "touched", Toast.LENGTH_SHORT).show();
            });

            // find elements
            TextView title = view.findViewById(R.id.resultItemTitle);
            TextView resultDesc = view.findViewById(R.id.resultItemText);
            ImageView img = view.findViewById(R.id.resultItemImage);

            title.setText(result.getTitle());
            resultDesc.setText(result.getResult());
        }

        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
