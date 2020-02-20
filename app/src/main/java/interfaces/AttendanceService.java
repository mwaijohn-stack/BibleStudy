package interfaces;

import com.google.gson.JsonElement;

import models.attendance.AttendanceDataSend;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AttendanceService {

    @POST("bsattendance/create")
    Call<JsonElement> createAttendance(@Body AttendanceDataSend attendanceDataSend);

}
