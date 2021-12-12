package com.github.wellwineo.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CalculatorViewActivity extends AppCompatActivity {

    // pages count
    private static final int NUM_PAGES = 8;

    // ViewPager2 instance
    private ViewPager2 viewPager;

    // adapter
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_view);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);

        pagerAdapter = new ScreenSlidePagerAdapter(this, NUM_PAGES);
        viewPager.setAdapter(pagerAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int index = bundle.getInt("index");
            Log.d("index", String.valueOf(index));
            viewPager.setCurrentItem(index);
        }
    }
}