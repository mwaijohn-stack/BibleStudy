package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.stepstone.stepper.StepperLayout;

public class RegisterActivity extends AppCompatActivity {
    private StepperLayout mStepperLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this));
    }
}
