package interfaces;

import com.google.gson.JsonElement;

import models.LoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MembersService {

    @FormUrlEncoded
    @POST("hostel/all")
    Call<JsonElement> getHostelsResponse(@Field("id") String id);
}
