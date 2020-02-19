package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    //private static final String BASE_URL = "https://bible.wasiliana.co.ke/";
   //private static final String BASE_URL = "http://192.168.1.250/bible-study-api/";
   private static final String BASE_URL = "https://bsv1.wasiliana.co.ke/";

    //private static final String BASE_URL = "http:///10.0.2.2/";


    public static Retrofit getRetrofitInstance() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
              @Override
              public Response intercept(Interceptor.Chain chain) throws IOException {
                  Request original = chain.request();

                  Request request = original.newBuilder()
                          .header("Content-type","application/json")
                          .header("X-AUTHENTICATION","MjU2N2E1ZWM5NzA1ZWI3YWMyYzk4NDAzM2UwNjE4OWQ=")
                          .method(original.method(), original.body())
                          .build();

                  return chain.proceed(request);
              }
        });

        OkHttpClient okHttpClient = httpClient.build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}

