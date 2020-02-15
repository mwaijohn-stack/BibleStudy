package fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.biblestudy.R;
import com.biblestudy.RegisterActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonElement;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.HostelService;
import interfaces.UniversityService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SharedPref;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFragment extends Fragment implements BlockingStep {
    MaterialSpinner university,campus,school,course,hostel;
    EditText input_hostel_no;
    SharedPreferences.Editor editor;

    public ArrayList<String> universities = new ArrayList<>();
    public Map<Integer, JSONArray> all_campuses = new HashMap<Integer, JSONArray>();
    public Map<Integer, Map<String, JSONArray>> all_schools = new HashMap<Integer, Map<String, JSONArray>>();

    ArrayList<String> my_campuses = new ArrayList<>();
    ArrayList<String> my_schools = new ArrayList<>();
    ArrayList<String> my_courses = new ArrayList<>();



    final String[] university_id = new String[1];
    final String[] campus_id = new String[1];
    final String[] school_id = new String[1];
    final String[] course_id = new String[1];

    public SchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_school, container, false);


        university =  root.findViewById(R.id.university);
        campus =  root.findViewById(R.id.campus);
        course =  root.findViewById(R.id.course);
        school =  root.findViewById(R.id.school);
        hostel =  root.findViewById(R.id.hostel);


        input_hostel_no = root.findViewById(R.id.input_hostel_no);

        UniversityService service = RetrofitClientInstance.getRetrofitInstance().create(UniversityService.class);
        Call<JsonElement> call = service.getUniversityResponse();

        final JSONObject[] jsonObject = {null};
        final JSONArray[] jsonArray = new JSONArray[1];
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                try {
                    jsonObject[0] = new JSONObject(response.body().toString());

                    jsonArray[0] = jsonObject[0].getJSONArray("message");

                    for (int i = 0; i< jsonArray[0].length(); i++){
                        JSONObject jsonObject1 = jsonArray[0].getJSONObject(i);

                        Log.d("=====university",jsonObject1.getString("name"));

                        universities.add(jsonObject1.getString("name"));
                    }

                    university.setItems(universities);

                    Log.d("all_university_size", String.valueOf(universities.size()));
                    Log.d("all_campuses_size",String.valueOf(all_campuses.size()));
                    Log.d("all_schools_size",String.valueOf(all_schools.size()));

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



        university.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                try {

                    JSONObject university_objects = jsonArray[0].getJSONObject(position);

                    university_id[0] = university_objects.getString("id");
                    Log.d("university_id", university_id[0]);

                    JSONArray campuses = university_objects.getJSONArray("campus");
                    Log.d("campuses__",String.valueOf(campuses.length()));



                    for (int i=0;i<campuses.length();i++){
                        JSONObject campuses_object = campuses.getJSONObject(i);
                        my_campuses.add(campuses_object.getString("name"));
                    }

                    String[] arr = my_campuses.toArray(new String[my_campuses.size()]);
                    //campus.setItems(arr);

                    Toast.makeText(getActivity(), "hghgh", Toast.LENGTH_SHORT).show();

                    campus.post(new Runnable() {
                        @Override
                        public void run() {
                            campus.setItems(arr);
                        }
                    });


                    campus.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                            Log.d("kjkj","kjkjk");
                            try {
                                JSONObject schools_objects = campuses.getJSONObject(position);

                                campus_id[0] = schools_objects.getString("id");
                                Log.d("campus_id", campus_id[0]);


                                HostelService service = RetrofitClientInstance.getRetrofitInstance().create(HostelService.class);
                                Call<JsonElement> call = service.getHostelsResponse(campus_id[0]);
                                call.enqueue(new Callback<JsonElement>() {
                                    @Override
                                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                        try {
                                            JSONObject hostel_response = new JSONObject(response.body().toString());
                                            Log.d("hostels_res",hostel_response.toString());
                                            JSONArray hostel_array = hostel_response.getJSONArray("message");

                                            if (hostel_array.length()>0){
                                              Log.d("hostels", String.valueOf(hostel_array.length()));

                                              ArrayList<String> hostel_name = new ArrayList<>();
                                              for (int i=0;i<hostel_array.length();i++){
                                                  JSONObject jsonObject1 = hostel_array.getJSONObject(i);
                                                  hostel_name.add(jsonObject1.getString("name"));
                                              }
                                              String[] arr = hostel_name.toArray(new String[hostel_name.size()]);
                                              hostel.setItems(arr);
                                            }else {
                                                Log.d("hostels","no hostels");
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Log.d("hostels",e.getMessage());
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<JsonElement> call, Throwable t) {

                                    }
                                });



                                JSONArray schools_array = schools_objects.getJSONArray("schools");

                                Log.d("schools_size", String.valueOf(schools_array.length()));
                                for (int j=0;j<schools_array.length();j++){
                                   JSONObject schools_object = schools_array.getJSONObject(j);
                                   my_schools.add(schools_object.getString("name"));
                                }

                                String[] arr = my_schools.toArray(new String[my_schools.size()]);

                                school.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        school.setItems(arr);
                                    }
                                });


                                school.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                                        try {
                                            //JSONObject schools_objects = campuses.getJSONObject(position);
                                            JSONObject courses_object = schools_array.getJSONObject(position);

                                            school_id[0] = courses_object.getString("id");
                                            Log.d("school_id", school_id[0]);

                                            JSONArray courses_array = courses_object.getJSONArray("courses");
                                            Log.d("courses_arr", String.valueOf(courses_array.length()));
                                            for (int j=0;j<courses_array.length();j++){
                                                JSONObject course_object = courses_array.getJSONObject(j);
                                                my_courses.add(course_object.getString("name"));
                                            }
                                            Log.d("courses_size", String.valueOf(schools_array.length()));

                                            String[] arr = my_courses.toArray(new String[my_courses.size()]);

                                            Log.d("arr_size", String.valueOf(arr.length));

                                            course.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    course.setItems(arr);
                                                }
                                            });

                                            course.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                                                    try {
                                                        JSONObject courses = courses_array.getJSONObject(position);
                                                        course_id[0] = courses.getString("id");

                                                        Log.d("course_id", course_id[0]);

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Log.d("courses_size", e.getMessage());
                                        }
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d("schools_size",e.getMessage());

                            }
                        }
                    });




                } catch (JSONException e) {
                    e.printStackTrace();

                    Log.d("error__",e.getMessage());
                }
            }
        });



        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        RegisterActivity registerActivity = (RegisterActivity) getActivity();
        ArrayList<String> university_data = registerActivity.getUniversities();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        if (university_id[0] == null || campus_id[0] == null
                || school_id[0] == null || course_id[0] == null
        || input_hostel_no.getText().toString().isEmpty()){
            Snackbar.make(getView(),"Enter all fields",Snackbar.LENGTH_SHORT).show();
        }else {

            SharedPref.write(SharedPref.UNIVERSITY_ID,university_id[0]);
            //Log.d("prefs", SharedPref.read(SharedPref.UNIVERSITY_ID,"no value"));

            SharedPref.write(SharedPref.CAMPUS_ID,campus_id[0]);
            SharedPref.write(SharedPref.SCHOOL_ID,school_id[0]);

            SharedPref.write(SharedPref.COURSE_ID,course_id[0]);
            SharedPref.write(SharedPref.HOSTEL_ID,String.valueOf(2));
            SharedPref.write(SharedPref.ROOM_NUMBER,input_hostel_no.getText().toString());

            callback.goToNextStep();

        }

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
