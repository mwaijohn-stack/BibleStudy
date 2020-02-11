package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

public class RegisterActivity extends AppCompatActivity implements SchoolData {
    private StepperLayout mStepperLayout;
    public ArrayList<String> universities = new ArrayList<>();
    public Map<Integer, JSONArray> all_campuses = new HashMap<Integer, JSONArray>();
    public Map<Integer, Map<String, JSONArray>> all_schools = new HashMap<Integer, Map<String, JSONArray>>();
    public Map<String,Map<String,JSONArray>> courses = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mStepperLayout =  findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this));




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
}
