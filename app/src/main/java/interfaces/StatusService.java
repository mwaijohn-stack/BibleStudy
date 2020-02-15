package interfaces;

import com.google.gson.JsonElement;

import models.StatusRequest;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface StatusService {

    @POST("status/members")
    Call<JsonElement> getStatusResponse(@Body StatusRequest statusRequest);
}
