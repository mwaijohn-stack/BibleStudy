package interfaces;

import com.google.gson.JsonElement;

import models.HostelRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CurrentSessionIdService {

    //campus request model used as parameters are the same
    @POST("session/sessionid")
    Call<JsonElement> getSessionId(@Body HostelRequest hostelRequest);
}
