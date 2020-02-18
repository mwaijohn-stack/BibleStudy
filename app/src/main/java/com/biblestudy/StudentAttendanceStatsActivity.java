package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import interfaces.StudentStatsService;
import models.StatsRequest;
import models.StatsResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;

public class StudentAttendanceStatsActivity extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_stats);

        pieChart = findViewById(R.id.py_chart);


        //student data from groupmembers activity
        String campus_id = getIntent().getStringExtra("campus_id");
        String student_id = getIntent().getStringExtra("student_id");

        Log.d("data",campus_id +"campus_id");
        Log.d("data",student_id +"student_id");

        //drawPieChart(25f,32f);


        StatsRequest statsRequest = new StatsRequest(student_id,campus_id);
        StudentStatsService service = RetrofitClientInstance.getRetrofitInstance().create(StudentStatsService.class);
        Call<JsonElement> call = service.getAttendanceStats(statsRequest);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.code()== 200){
                    Log.d("success","WORKED FINE");

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().toString());

                        JSONObject jsonObject1 = jsonObject.getJSONObject("message");

                        Gson gson = new Gson();
                        Type type = new TypeToken<StatsResponseModel>(){}.getType();
                        StatsResponseModel stats = gson.fromJson(String.valueOf(jsonObject1), type);

                        Log.d("attended",stats.getAttendedWeeks());
                        Log.d("attended_tot",stats.getWeeks());

                        int attended = Integer.parseInt(stats.getAttendedWeeks());
                        int missed = Integer.parseInt(stats.getWeeks()) - Integer.parseInt(stats.getAttendedWeeks());;

                        drawPieChart(attended,missed);
                        //drawPieChart(25f,32f);

                        Log.d("fvalue", String.valueOf(attended) +" " + missed);

                    } catch (JSONException e) {
                        e.printStackTrace();

                        Log.d("exce",e.getMessage());

                        //drawPieChart(50,50);

                    }


                    Log.d("data",jsonObject.toString());
                }
                Log.d("success",String.valueOf(response.code()));

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("success",t.getMessage());
            }
        });
    }

    public void drawPieChart(int attended,int missed){


        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationEnabled(true);

        //set speed of deceleration increase the coefficient for a smooth deceleration
        pieChart.setDragDecelerationFrictionCoef(0.85f);
        //set the center hole can also set the color
        pieChart.setDrawHoleEnabled(true);

        Description description = new Description();
        description.setText("Attendance History");
        description.setTextSize(25f);

        pieChart.setDescription(description);

        //animate
        pieChart.animateY(1000);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(attended,"Attended"));
        pieEntries.add(new PieEntry(missed,"Not attended"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Attendance");

        //add and reduce the space between pies
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);


        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);

        pieChart.setData(pieData);
    }
}
