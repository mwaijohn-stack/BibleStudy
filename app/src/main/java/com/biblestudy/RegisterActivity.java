package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.JsonElement;
import com.stepstone.stepper.StepperLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.SchoolData;
import interfaces.UniversityService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SharedPref;

public class RegisterActivity extends AppCompatActivity implements SchoolData {
    private StepperLayout mStepperLayout;
    public ArrayList<String> universities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Sign Up");

        mStepperLayout =  findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this));

        SharedPref.init(getApplicationContext());
    }

    @Override
    public ArrayList<String> getUniversities() {
        return universities;
    }

    @Override
    public void getCampus(String university_id) {

    }

    @Override
    public void getSchools(String campus_id) {

    }

    @Override
    public void getCourses(String school_id) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_logout:

                finish();

                break;
            default:
                break;
        }

        return true;
    }
}
