package interfaces;

import com.google.gson.JsonElement;

import models.IsRegisteredRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BSRegistrationStatusService {

    @POST("session/status")
    Call<JsonElement> getRegistrationStatus(@Body IsRegisteredRequest isRegisteredRequest);

}
