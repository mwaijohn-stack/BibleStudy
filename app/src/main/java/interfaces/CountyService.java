package interfaces;

import com.google.gson.JsonElement;

import models.LoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface CountyService {

    @GET("county/all")
    Call<JsonElement> getCountyResponse();
}
