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

import interfaces.SchoolData;
import interfaces.UniversityService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;

public class RegisterActivity extends AppCompatActivity implements SchoolData {
    private StepperLayout mStepperLayout;
    public ArrayList<String> universities = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mStepperLayout =  findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this));

        UniversityService service = RetrofitClientInstance.getRetrofitInstance().create(UniversityService.class);
        Call<JsonElement> call = service.getUniversityResponse();

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //Log.d("universities",response.body().toString());

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().toString());

                    JSONArray jsonArray = jsonObject.getJSONArray("message");
                    //Log.d("massage", String.valueOf(jsonArray.length()));

                    //create a relationship  tree
                    //get university list
                            //get campus list
                                //get campus school list
                                    //get course
                                            //get hostel
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        //Log.d("msg",jsonObject1.getString("id"));
                        JSONArray campuses = jsonObject1.getJSONArray("campus");

                        Log.d("=====university",jsonObject1.getString("name"));

                        universities.add(jsonObject1.getString("name"));

                        //get campuses
                        if (campuses.length()>0){
                            //get campuses
                           for (int j=0;j<campuses.length();j++){
                               //get schools
                               JSONObject campusObject = campuses.getJSONObject(j);
                               Log.d("=================campus",campusObject.getString("name"));

                               JSONArray schools = campusObject.getJSONArray("schools");
                               for (int z=0;z<schools.length();z++){
                                 //get courses
                                 JSONObject courseObject = schools.getJSONObject(z);
                                 Log.d("=============school",courseObject.getString("name"));

                                   JSONArray courses = courseObject.getJSONArray("courses");


                                 for (int y=0;y<courses.length();y++){
                                     JSONObject courseArr = courses.getJSONObject(y);
                                     Log.d("======courses",courseArr.getString("name"));
                                 }
                               }
                           }
                        }
                    }

                    Log.d("university_size", String.valueOf(universities.size()));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("massage",e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("university",t.getMessage());
            }
        });
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
