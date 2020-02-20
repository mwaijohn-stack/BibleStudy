package interfaces;

import com.google.gson.JsonElement;

import models.SessionRegistrationRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RegisterForBsSessionService {

    @POST("session/join/{registration_session_id}")
    Call<JsonElement> registerForBs(@Path(value = "registration_session_id", encoded = true)  String registration_session_id,
                                       @Body SessionRegistrationRequest sessionRegistrationRequest);
}
