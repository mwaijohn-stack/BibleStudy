package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import interfaces.CurrentSessionIdService;
import interfaces.RegisterForBsSessionService;
import models.HostelRequest;

import models.SessionRegistrationRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SharedPref;

public class RegisterBibleStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_bible_study);


        HostelRequest hostelRequest = new HostelRequest(SharedPref.read(SharedPref.CAMPUS_ID,"0"));
        CurrentSessionIdService hostel_service = RetrofitClientInstance.getRetrofitInstance().create(CurrentSessionIdService.class);
        Call<JsonElement> call = hostel_service.getSessionId(hostelRequest);


        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                Log.d("sess_id",response.body().toString());

                JSONObject jsonObject = null;
                String session_id = "";
                try {
                    jsonObject = new JSONObject(response.body().toString());
                    session_id = jsonObject.getString("message");

                    Log.d("ses_id",session_id);

                } catch (JSONException e) {
                    Log.d("ses_id",e.getMessage());
                    e.printStackTrace();
                }

                SessionRegistrationRequest sessionRegistrationRequest =
                        new SessionRegistrationRequest(SharedPref.read(SharedPref.STUDENT_ID,"0"));

                RegisterForBsSessionService service = RetrofitClientInstance.getRetrofitInstance().create(RegisterForBsSessionService.class);
                Call<JsonElement> request_call = service.registerForBs(session_id,sessionRegistrationRequest);

                request_call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                        Log.d("session", String.valueOf(response.code()));

                        Log.d("session", String.valueOf(response.raw().request().url()));
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        Log.d("session",t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("sess_id",t.getMessage());
            }
        });
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
