package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import interfaces.LoginService;
import models.LoginRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SharedPref;
import utilities.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText phone_no,reg_no;
    AppCompatButton btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone_no = findViewById(R.id.phone_no);
        reg_no = findViewById(R.id.reg_no);
        btn_login  = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);

        SharedPref.init(this);

    }

    public void login(String phone, String reg_no){

        //LoginRequest loginRequest = new LoginRequest("254728433879","CD/19/12");
        LoginRequest loginRequest = new LoginRequest(phone,reg_no);
        LoginService service = RetrofitClientInstance.getRetrofitInstance().create(LoginService.class);
        Call<JsonElement> call = service.getLoginResponse(loginRequest);
        android.app.ProgressDialog progress = Utils.progress(MainActivity.this,"Authenticating");
        progress.show();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                try {
                    Log.d("sdsd", String.valueOf(response.code()));

                    if(response.code() == 404){
                        phone_no.setError("Wrong number or password");
                        Toast.makeText(MainActivity.this, "WRONG DETAILS ENTERED!!!!",Toast.LENGTH_LONG).show();

                    }else if(response.code() == 200){
                        JSONObject jsonObject = new JSONObject(response.body().toString());
                        JSONObject jsonObject2  = jsonObject.getJSONObject("message");
                        //JSONArray HGH =  jsonObject.getJSONArray("massage");

                        if(jsonObject.has("message")){
                            Toast.makeText(MainActivity.this, "has massage", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, jsonObject2.getString("first_name"), Toast.LENGTH_SHORT).show();

                            SharedPref.write(SharedPref.STUDENT_ID,jsonObject2.getString("id"));
                        }else {
                            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(MainActivity.this, "LOGGED IN!!!!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this, GroupMembers.class));
                    }
                    progress.dismiss();
                } catch (JSONException e) {
                    //e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        String phone = phone_no.getText().toString();
        String reg = reg_no.getText().toString();

        if (phone.isEmpty() || reg.isEmpty()){
            phone_no.setError("Enter all fields");
        }else {
            login(phone,reg);
        }
    }
}
