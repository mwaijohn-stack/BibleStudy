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

import com.biblestudy.R;
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
import java.util.Iterator;
import java.util.Map;

import interfaces.CountyService;
import interfaces.RegisterService;
import models.Registration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SharedPref;


/**
 * A simple {@link Fragment} subclass.
 */
public class YearFragment extends Fragment implements BlockingStep {

    HashMap<Integer,String> counties_map = new HashMap<>();
    MaterialSpinner counties_spinner,year_spinner;
    SharedPreferences.Editor editor;


    public YearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_year, container, false);

        counties_spinner = root.findViewById(R.id.county);
        year_spinner = root.findViewById(R.id.year);
        String[] year = {"1","2","3","4","5","6"};
        year_spinner.setItems(year);

        year_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                SharedPref.write(SharedPref.YEAR_OF_STUDY,String.valueOf(year[position]));
            }
        });


        CountyService service = RetrofitClientInstance.getRetrofitInstance().create(CountyService.class);
        Call<JsonElement> call = service.getCountyResponse();

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    JSONArray counties = jsonObject.getJSONArray("message");

                    for (int i=0;i<counties.length();i++){
                        JSONObject county = counties.getJSONObject(i);
                        counties_map.put(county.getInt("id"),county.getString("name"));
                    }


                    ArrayList<String> data = new ArrayList<>();
                    Iterator<Map.Entry<Integer, String>> it = counties_map.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<Integer, String> pair = (Map.Entry<Integer, String>) it.next();
                        data.add(pair.getValue());

                        Log.d("added",pair.getValue());
                    }
                    counties_spinner.setItems(data);
                    counties_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                            try {
                                JSONObject county_object = counties.getJSONObject(position);
                                SharedPref.write(SharedPref.COUNTY_ID,county_object.getString("id"));
//                                editor.putString(SharedPref.COUNTY_ID,county_object.getString("id"));
//                                editor.apply();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
        return root;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


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

        //callback.complete();

        String first_name = SharedPref.read(SharedPref.FIRST_NAME,null);
        String middle_name = SharedPref.read(SharedPref.SECOND_NAME,null);
        String last_name = SharedPref.read(SharedPref.LAST_NAME,null);
        String registration_no = SharedPref.read(SharedPref.REG_NUMBER,null);
        String msisdn = SharedPref.read(SharedPref.PHONE_NUMBER,null);
        String gender = SharedPref.read(SharedPref.GENDER,null);
        String university_id = SharedPref.read(SharedPref.UNIVERSITY_ID,null);
        String campus_id = SharedPref.read(SharedPref.CAMPUS_ID,null);
        String school_id = SharedPref.read(SharedPref.SCHOOL_ID,null);
        String course_id = SharedPref.read(SharedPref.COURSE_ID,null);
        String hostel_id = SharedPref.read(SharedPref.HOSTEL_ID,null);
        String room_no = SharedPref.read(SharedPref.ROOM_NUMBER,null);
        String year_of_study = SharedPref.read(SharedPref.YEAR_OF_STUDY,null);
        String county_id = SharedPref.read(SharedPref.COUNTY_ID,null);


        Log.d("first_name",first_name +"=" + middle_name +"=" + last_name +"=" +
                registration_no +"=" + msisdn +"=" + gender +"=" + county_id +"=" +year_of_study);

        Registration registration = new Registration(first_name,middle_name,last_name,
                msisdn,gender,county_id,campus_id,registration_no,
                course_id,hostel_id,"zone",
                "hostel name",room_no,"2",year_of_study);

        RegisterService service = RetrofitClientInstance.getRetrofitInstance().create(RegisterService.class);
        Call<JsonElement> call = service.registerStudent(registration);


        Log.d("completed","year fragment complete");

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()){
                    Log.d("registration",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("registration",t.getMessage());
            }
        });

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
