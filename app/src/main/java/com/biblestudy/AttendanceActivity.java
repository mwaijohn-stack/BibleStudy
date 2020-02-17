package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonElement;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Map;

import adapters.AttendanceAdapter;
import interfaces.AttendanceService;
import models.AttendanceRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SimpleDividerItemDecoration;

import static adapters.AttendanceAdapter.attendanceRequests;

public class AttendanceActivity extends AppCompatActivity {
    MaterialSpinner week_number;
    AppCompatButton btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        btn_submit = findViewById(R.id.btn_submit);

        week_number = findViewById(R.id.week_number);
        week_number.setItems("Week 1", "Week 2", "Week 3", "Week 4", "Week 5");

        AttendanceAdapter membersAdapter = new AttendanceAdapter(GroupMembersActivity.new_list, getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(membersAdapter);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttendanceService service = RetrofitClientInstance.getRetrofitInstance().create(AttendanceService.class);

                for (Map.Entry<String, AttendanceRequest> entry : attendanceRequests.entrySet()) {
                    AttendanceRequest attendanceRequest = entry.getValue();
                    Call<JsonElement> call = service.createAttendnce(attendanceRequest);

                    call.enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            Log.d("saved_attendance","student " + attendanceRequest.getStudentId() + " saved");
                            Log.d("saved_attendance","student " + response.code() + " saved");

                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {

                            Log.d("saved_attendance","student " + attendanceRequest.getStudentId() + " failed to saved");

                        }
                    });

                }
            }
        });
    }
}
