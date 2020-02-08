package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import interfaces.LoginService;
import models.LoginRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("data_","on_create");
    }

    public void login(View view){

        LoginRequest loginRequest = new LoginRequest("254728433879","CD/19/12");
        LoginService service = RetrofitClientInstance.getRetrofitInstance().create(LoginService.class);
        Call<JsonElement> call = service.getLoginResponse(loginRequest);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    JSONObject jsonObject2  = jsonObject.getJSONObject("message");
                    //JSONArray HGH =  jsonObject.getJSONArray("massage");

                    if(jsonObject.has("message")){
                        Toast.makeText(MainActivity.this, "has massage", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, jsonObject2.getString("first_name"), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    }

                    //Toast.makeText(MainActivity.this, jsonObject2.toString(),Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
