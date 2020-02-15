package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jaredrummler.materialspinner.MaterialSpinner;

import adapters.AttendanceAdapter;
import adapters.MembersAdapter;
import utilities.SimpleDividerItemDecoration;

public class AttendanceActivity extends AppCompatActivity {
    MaterialSpinner week_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        week_number = findViewById(R.id.week_number);
        week_number.setItems("Week 1","Week 2","Week 3","Week 4","Week 5");


        AttendanceAdapter membersAdapter = new AttendanceAdapter(GroupMembersActivity.new_list,getApplicationContext());

        RecyclerView recyclerView =  findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(membersAdapter);
    }
}
