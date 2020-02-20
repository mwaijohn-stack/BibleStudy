package interfaces;


import models.update.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentDetailsService {

    @GET("status/student/details/{id}")
    Call<ResponseBody> getStudentData(@Path("id") String id);
}
