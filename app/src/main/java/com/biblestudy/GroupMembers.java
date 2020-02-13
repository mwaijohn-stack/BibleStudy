package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import adapters.MembersAdapter;
import interfaces.LoginService;
import interfaces.StatusService;
import models.GroupMember;
import models.LoginRequest;
import models.StatusRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SharedPref;

public class GroupMembers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_members);

        StatusRequest statusRequest = new StatusRequest(SharedPref.read(SharedPref.STUDENT_ID,"0"));
        StatusService service = RetrofitClientInstance.getRetrofitInstance().create(StatusService.class);
        Call<JsonElement> call = service.getStatusResponse(statusRequest);


        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                if (response.code() == 200){
                    Log.d("success",response.body().toString());
                }

                JSONObject jsonObject = null;
                try {
                    Gson gson = new Gson();
                    jsonObject = new JSONObject(response.body().toString());

                    JSONArray jsonArray = jsonObject.getJSONArray("message");

                    Type listType = new TypeToken<List<GroupMember>>(){}.getType();
                    List<GroupMember> list = new ArrayList<>();
                    List<GroupMember> new_list = new ArrayList<>();

                    list = gson.fromJson(String.valueOf(jsonArray), listType);

                    Log.d("ghgg", String.valueOf(list.size()));

                    for (GroupMember hg:
                         list) {
                        new_list.add(hg);
                    }

                    MembersAdapter membersAdapter = new MembersAdapter(new_list,getApplicationContext());

                    RecyclerView recyclerView =  findViewById(R.id.recycler_view);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(membersAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Log.d("success",response.message());
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
             Log.d("success",t.getMessage());
            }
        });
    }
}
