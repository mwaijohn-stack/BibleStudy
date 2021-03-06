package com.biblestudy;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import fragments.BioFragment;
import fragments.SchoolFragment;
import fragments.YearFragment;

public class MyStepperAdapter extends AbstractFragmentStepAdapter {
    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";

    public MyStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position){
            case 0:
                final BioFragment step1 = new BioFragment();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
                step1.setArguments(b1);
                return step1;
            case 1:
                final SchoolFragment step2 = new SchoolFragment();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                step2.setArguments(b2);
                return step2;
            case 2:
                final YearFragment step3 = new YearFragment();
                Bundle b3 = new Bundle();
                b3.putInt(CURRENT_STEP_POSITION_KEY, position);
                step3.setArguments(b3);
                return step3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types

        switch (position){
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("Bio Info") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Academic") //can be a CharSequence instead
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("Level") //can be a CharSequence instead
                        .create();
        }
        return null;
    }
}
