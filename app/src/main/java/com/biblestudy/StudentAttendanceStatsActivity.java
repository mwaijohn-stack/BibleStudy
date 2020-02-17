package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import interfaces.StudentStatsService;
import models.AttendanceRequest;
import models.StatsRequest;
import models.StatsResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;

public class StudentAttendanceStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_stats);

        //student data from groupmembers activity
        String campus_id = getIntent().getStringExtra("campus_id");
        String student_id = getIntent().getStringExtra("student_id");

        Log.d("data",campus_id +"campus_id");
        Log.d("data",student_id +"student_id");


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
                        Log.d("attended",stats.getWeeks());

                        Float attended = Float.parseFloat(stats.getAttendedWeeks());
                        Float missed = Float.parseFloat(stats.getWeeks()) - Float.parseFloat(stats.getAttendedWeeks());;

                        drawPieChart(attended,missed);


                    } catch (JSONException e) {
                        e.printStackTrace();

                        Log.d("exce",e.getMessage());
                        drawPieChart(5f,95f);

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

    public void drawPieChart(Float dim1,Float dim2){

        PieChart pieChart = findViewById(R.id.py_chart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationEnabled(true);
        pieChart.setDragDecelerationFrictionCoef(0.85f);
        pieChart.setDrawHoleEnabled(true);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(dim1,"Attended"));
        pieEntries.add(new PieEntry(dim2,"Not attended"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Attendance");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);


        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);

        pieChart.setData(pieData);
    }
}
