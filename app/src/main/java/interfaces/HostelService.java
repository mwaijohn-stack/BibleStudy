package interfaces;

import com.google.gson.JsonElement;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface HostelService {

//    @GET("hostel/all")
//    Call<JsonElement> getHostelsResponse(@QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST("hostel/all")
    Call<JsonElement> getHostelsResponse(@Field("id") String id);

}
