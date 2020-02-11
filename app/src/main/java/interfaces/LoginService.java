package interfaces;

import com.google.gson.JsonElement;

import models.LoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface LoginService {

        @POST("student/login")
        Call<JsonElement> getLoginResponse(@Body LoginRequest loginRequest);
}
