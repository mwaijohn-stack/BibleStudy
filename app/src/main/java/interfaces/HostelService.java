package interfaces;

import com.google.gson.JsonElement;

import models.HostelRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HostelService {


    @POST("hostels/list")
    @Headers("Content-Type: application/json")
    Call<JsonElement> getHostelsResponse(@Body HostelRequest hostelRequest);

}
