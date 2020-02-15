package interfaces;

import com.google.gson.JsonElement;

import models.Registration;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {

    @POST("student/create")
    Call<JsonElement> registerStudent(@Body Registration registration);
}
