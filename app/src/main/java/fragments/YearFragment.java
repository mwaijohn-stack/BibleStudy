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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;


/**
 * A simple {@link Fragment} subclass.
 */
public class YearFragment extends Fragment implements BlockingStep {

    HashMap<Integer,String> counties_map = new HashMap<>();
    MaterialSpinner counties_spinner,year_spinner;

    public YearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_year, container, false);

        counties_spinner = root.findViewById(R.id.county);
        year_spinner = root.findViewById(R.id.year);

        year_spinner.setItems(1,2,3,4,5,6);

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
        Log.d("completed","year fragment complete");
        callback.complete();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
