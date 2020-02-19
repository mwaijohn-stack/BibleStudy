package interfaces;

import com.google.gson.JsonElement;

import models.StatsRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface StudentStatsService {

    @POST("bsattendance/get_one_students_attended_weeks")
    Call<JsonElement> getAttendanceStats(@Body StatsRequest statsRequest);

}
