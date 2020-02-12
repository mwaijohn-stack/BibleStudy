package fragments;


import android.content.Context;
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
import java.util.TreeMap;

import interfaces.UniversityService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFragment extends Fragment implements BlockingStep {
    MaterialSpinner university,campus,school,course,hostel;
    EditText input_hostel_no;

    public ArrayList<String> universities = new ArrayList<>();
    public Map<Integer, JSONArray> all_campuses = new HashMap<Integer, JSONArray>();
    public Map<Integer, Map<String, JSONArray>> all_schools = new HashMap<Integer, Map<String, JSONArray>>();

    ArrayList<String> my_campuses = new ArrayList<>();
    ArrayList<String> my_schools = new ArrayList<>();
    ArrayList<String> my_courses = new ArrayList<>();

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
                //Log.d("universities",response.body().toString());


                try {
                    jsonObject[0] = new JSONObject(response.body().toString());

                    jsonArray[0] = jsonObject[0].getJSONArray("message");

                    for (int i = 0; i< jsonArray[0].length(); i++){
                        JSONObject jsonObject1 = jsonArray[0].getJSONObject(i);
                        String university_id = jsonObject1.getString("id");
                        //Log.d("msg",jsonObject1.getString("id"));
                        JSONArray campuses = jsonObject1.getJSONArray("campus");

                        Log.d("=====university",jsonObject1.getString("name"));

                        universities.add(jsonObject1.getString("name"));

                        //get campuses
//                        if (campuses.length()>0){
//                            //get campuses
//                            for (int j=0;j<campuses.length();j++){
//                                //get schools
//                                JSONObject campusObject = campuses.getJSONObject(j);
//                                Log.d("=================campus",campusObject.getString("name"));
//                                String campus_id = campusObject.getString("id");
//
//                                JSONArray schools = campusObject.getJSONArray("schools");
//
//                                all_campuses.put(j,schools);
//                                my_campuses.add(university_id +"_"+ campusObject.getString("name"));
//
//                                Map<String,JSONArray> scl = new HashMap<>();
//                                for (int z=0;z<schools.length();z++){
//                                    //get courses
//                                    JSONObject courseObject = schools.getJSONObject(z);
//                                    Log.d("=============school",courseObject.getString("name"));
//
//                                    JSONArray courses = courseObject.getJSONArray("courses");
//                                    scl.put(campusObject.getString("name"),schools);
//                                    all_schools.put(z,scl);
//                                    my_schools.add(university_id +"_" + campus_id +"_" + courseObject.getString("name"));
//
//                                    for (int y=0;y<courses.length();y++){
//                                        JSONObject courseArr = courses.getJSONObject(y);
//                                        Log.d("======courses",courseArr.getString("name"));
//                                        my_courses.add(courseArr.getString("name"));
//                                    }
//                                }
//                            }
//                        }
                    }

                    university.setItems(universities);
                    //campus.setItems(my_campuses);
                    //school.setItems(my_schools);
                    //course.setItems(my_courses);

                    Log.d("all_university_size", String.valueOf(universities.size()));
                    Log.d("all_campuses_size",String.valueOf(all_campuses.size()));
                    Log.d("all_schools_size",String.valueOf(all_schools.size()));

                    //JSONArray aasas = all_campuses.get("Moi University");
                    //Log.d("aasas", String.valueOf(aasas.length()));

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

                                            JSONArray courses_array = courses_object.getJSONArray("courses");

                                            for (int j=0;j<courses_array.length();j++){
                                                JSONObject course_object = courses_array.getJSONObject(j);
                                                my_courses.add(course_object.getString("name"));
                                            }
                                            Log.d("courses_size", String.valueOf(schools_array.length()));

                                            String[] arr = my_courses.toArray(new String[my_courses.size()]);

                                            course.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    course.setItems(arr);
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
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
