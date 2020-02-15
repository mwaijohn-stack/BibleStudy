package interfaces;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;


public interface UniversityService {

    @GET("university/list")
    Call<JsonElement> getUniversityResponse();
}
