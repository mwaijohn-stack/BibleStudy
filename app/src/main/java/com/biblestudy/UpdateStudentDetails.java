package com.biblestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import interfaces.StudentDetailsService;

import interfaces.studentupdate.StudentBioUpdateService;
import models.update.PostUpdateRequest;
import models.update.ResponseBody;
import models.update.ResponseBodyString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.RetrofitClientInstance;
import utilities.SharedPref;
import utilities.Utils;

public class UpdateStudentDetails extends AppCompatActivity {
    MaterialSpinner year_spinner;
    EditText f_name,middle_name,last_name,registration_no,phone_no;
    AppCompatButton btn_update_bio;
    String[] gender = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_details);

        setTitle("Update Details");
        f_name = findViewById(R.id.input_first_name);
        middle_name = findViewById(R.id.input_middle_name);
        last_name = findViewById(R.id.input_last_name);
        registration_no = findViewById(R.id.input_reg);
        phone_no = findViewById(R.id.input_phone);

        btn_update_bio = findViewById(R.id.btn_update_bio);

        year_spinner = findViewById(R.id.year);
        String[] year = {"1","2","3","4","5","6"};
        year_spinner.setItems(year);


        MaterialSpinner spinner = findViewById(R.id.spinner);

        gender = new String[]{"Male", "Female"};
        spinner.setItems(gender);
        SharedPref.write(SharedPref.GENDER, "Male");

        year_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                SharedPref.write(SharedPref.YEAR_OF_STUDY,String.valueOf(year[position]));
            }
        });

        android.app.ProgressDialog progress = Utils.progress(UpdateStudentDetails.this,"Fetching details");

        StudentDetailsService service = RetrofitClientInstance.getRetrofitInstance().create(StudentDetailsService.class);
        Call<ResponseBody> call = service.getStudentData(SharedPref.read(SharedPref.STUDENT_ID,"0"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                models.update.Massage massage = responseBody.getMessage();

                f_name.setText(massage.getFirstName());
                middle_name.setText(massage.getMiddleName());
                last_name.setText(massage.getLastName());
                registration_no.setText(massage.getRegistrationNumber());
                phone_no.setText(massage.getMsisdn());

                year_spinner.setSelectedIndex(0);

                Log.d("sdetails",massage.getFirstName());

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("sdetails",t.getMessage());
                Toast.makeText(UpdateStudentDetails.this, "Failed to update details", Toast.LENGTH_LONG).show();
                progress.dismiss();
            }
        });


        btn_update_bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("update_status",SharedPref.read(SharedPref.STUDENT_ID,"0"));
                Log.d("update_status",phone_no.getText().toString());
                Log.d("update_status",last_name.getText().toString());
                Log.d("update_status",middle_name.getText().toString());
                Log.d("update_status",gender[spinner.getSelectedIndex()]);
                Log.d("update_status",f_name.getText().toString());

                PostUpdateRequest postUpdateRequest = new PostUpdateRequest(
                        f_name.getText().toString(),
                        middle_name.getText().toString(),
                        last_name.getText().toString(),
                        registration_no.getText().toString(),
                        phone_no.getText().toString(),
                        gender[spinner.getSelectedIndex()]);


                StudentBioUpdateService service = RetrofitClientInstance.getRetrofitInstance().create(StudentBioUpdateService.class);
                Call<ResponseBodyString> call = service.updateBio( SharedPref.read(SharedPref.STUDENT_ID,"0"),postUpdateRequest);
                android.app.ProgressDialog progress_ = Utils.progress(UpdateStudentDetails.this,"Updating details");
                progress_.show();
                call.enqueue(new Callback<ResponseBodyString>() {
                    @Override
                    public void onResponse(Call<ResponseBodyString> call, Response<ResponseBodyString> response) {
                        Log.d("update_status",response.body().getMessage());

                        Log.d("update_status",call.request().url().toString());

                        progress_.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ResponseBodyString> call, Throwable t) {
                        Log.d("update_status",t.getMessage());
                       progress_.dismiss();
                    }
                });
            }
        });
    }
}