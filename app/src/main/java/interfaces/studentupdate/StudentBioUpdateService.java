package interfaces.studentupdate;

import models.update.PostUpdateRequest;
import models.update.ResponseBodyString;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StudentBioUpdateService {

    @POST("status/student/update/{id}")  //status/student/update/872
    Call<ResponseBodyString> updateBio(@Path("id") String id, @Body PostUpdateRequest postUpdateRequest);
}
