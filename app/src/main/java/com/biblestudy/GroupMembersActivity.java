package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapters.MembersAdapter;
import interfaces.StatusService;
import models.GroupMember;
import models.StatusRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SharedPref;
import utilities.SimpleDividerItemDecoration;

public class GroupMembersActivity extends AppCompatActivity {

    public static List<GroupMember> new_list = new ArrayList<>();

    public  static FloatingActionButton create_attendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_members);

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        create_attendance = findViewById(R.id.create_attendance);
        setTitle("Group " + SharedPref.read(SharedPref.GROUP_NAME,"") + " Members");

        StatusRequest statusRequest = new StatusRequest(SharedPref.read(SharedPref.STUDENT_ID,"0"));
        StatusService service = RetrofitClientInstance.getRetrofitInstance().create(StatusService.class);
        Call<JsonElement> call = service.getStatusResponse(statusRequest);

        Log.d("student_id",SharedPref.read(SharedPref.STUDENT_ID,"0"));

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                Log.d("response__",response.body().toString());
                Log.d("response__", String.valueOf(response.code()));
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
                    new_list = new ArrayList<>();

                    list = gson.fromJson(String.valueOf(jsonArray), listType);

                    Log.d("ghgg", String.valueOf(list.size()));

                    for (GroupMember hg:
                         list) {
                        new_list.add(hg);
                    }

                    MembersAdapter membersAdapter = new MembersAdapter(new_list,getApplicationContext());

                    RecyclerView recyclerView =  findViewById(R.id.recycler_view);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(membersAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();

                    Log.d("failed_to","failed to get group members");
                }


                Log.d("success",response.message());
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
             Log.d("success",t.getMessage());
            }
        });
    }

    public void createAttendance(View view){
        //Snackbar.make(view,"Create attendance",Snackbar.LENGTH_SHORT).show();
        startActivity(new Intent(this,AttendanceActivity.class));
    }
}
