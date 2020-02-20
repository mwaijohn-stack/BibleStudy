package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adapters.AttendanceAdapter;
import interfaces.AttendanceService;
import models.attendance.AttendanceDataSend;
import models.attendance.AttendanceRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SimpleDividerItemDecoration;
import utilities.Utils;

import static adapters.AttendanceAdapter.attendanceRequests;

public class AttendanceActivity extends AppCompatActivity {
    AppCompatButton btn_submit;
    ProgressBar progressBar;
    List<AttendanceRequest> list_attendance = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        btn_submit = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progress);

        android.app.ProgressDialog progress = Utils.progress(AttendanceActivity.this,"Authenticating");

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
                progressBar.setVisibility(View.VISIBLE);

                AttendanceService service = RetrofitClientInstance.getRetrofitInstance().create(AttendanceService.class);

                Utils.progress(getApplicationContext(),"Updating attendance").show();

                AttendanceDataSend attendanceDataSend = new AttendanceDataSend();
                attendanceDataSend.setData(list_attendance);

                for (Map.Entry<String, AttendanceRequest> entry : attendanceRequests.entrySet()) {

                    AttendanceRequest attendanceRequest = entry.getValue();
                    list_attendance.add(attendanceRequest);
//                    Call<JsonElement> call = service.createAttendnce(attendanceRequest);
//
//                    call.enqueue(new Callback<JsonElement>() {
//                        @Override
//                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
//
//                            Log.d("saved_attendance","student " + attendanceRequest.getStudentId() + " saved");
//                            Log.d("saved_attendance","student " + response.code() + " saved");
//                            Log.d("saved_attendance","student " + response.message() + " saved");
//                        }
//
//                        @Override
//                        public void onFailure(Call<JsonElement> call, Throwable t) {
//
//                            Log.d("saved_attendance",t.getMessage()  + " failed to saved");
//
//                        }
//                    });
                }

                Call<JsonElement> call = service.createAttendance(attendanceDataSend);

                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        //Log.d("attendance_", String.valueOf(response.code()));
                        Log.d("attendance_", String.valueOf(response.message()));
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        Log.d("attendance_",t.getMessage());
                    }
                });

                progressBar.setVisibility(View.GONE);
                Toast.makeText(AttendanceActivity.this, "Attendance updated", Toast.LENGTH_LONG).show();
               // finish();
            }
        });
    }
}
