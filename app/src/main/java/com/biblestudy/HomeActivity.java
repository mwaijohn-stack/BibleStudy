package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    AppCompatButton btn_register_bs,btn_view_attendance,btn_view_group_members;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // btn_register_bs = findViewById(R.id.btn_registerbs);
       btn_view_attendance = findViewById(R.id.btn_view_attendance);
        btn_view_group_members = findViewById(R.id.btn_view_members);

        setTitle("Home");

//        btn_register_bs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this,RegisterBibleStudyActivity.class));
//            }
//        });

        btn_view_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,StudentAttendanceStatsActivity.class));
            }
        });

        btn_view_group_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, GroupMembersActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                finish();
                break;
            case R.id.action_update:
                startActivity(new Intent(HomeActivity.this, UpdateStudentDetails.class));

                break;
            default:
                break;
        }

        return true;
    }
}
