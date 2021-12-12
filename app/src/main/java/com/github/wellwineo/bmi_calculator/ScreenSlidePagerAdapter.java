package com.github.wellwineo.bmi_calculator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.github.wellwineo.bmi_calculator.CalculateFragments.BMI;
import com.github.wellwineo.bmi_calculator.CalculateFragments.Cardio;
import com.github.wellwineo.bmi_calculator.CalculateFragments.FunctionalChanges;
import com.github.wellwineo.bmi_calculator.CalculateFragments.LifeIndex;
import com.github.wellwineo.bmi_calculator.CalculateFragments.MotorAbility;
import com.github.wellwineo.bmi_calculator.CalculateFragments.SkibinskiIndex;
import com.github.wellwineo.bmi_calculator.CalculateFragments.Stamina;
import com.github.wellwineo.bmi_calculator.CalculateFragments.VegetativeIndex;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {

    private int pages;
    public ScreenSlidePagerAdapter(FragmentActivity fa, int pages) {
        super(fa);
        this.pages = pages;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        /*
         * Routing
         */
        switch (position){
            case 0: return new BMI();
            case 1: return new MotorAbility();
            case 2: return new Stamina();
            case 3: return new Cardio();
            case 4: return new LifeIndex();
            case 5: return new SkibinskiIndex();
            case 6: return new VegetativeIndex();
            case 7: return new FunctionalChanges();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return pages;
    }
}
