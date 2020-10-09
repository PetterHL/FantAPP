package no.ntnu.PetterHL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //URL AT SCHOOL
    private static final String BASE_URL = "http://10.22.194.55:8080/mavenproject1/api/";
    private static ApiClient SINGLETON;
    Retrofit retrofit = null;


    public ApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    // Checks if an instance has been created or not
    public static synchronized ApiClient getSINGLETON() {
        if (SINGLETON == null) {
            SINGLETON = new ApiClient();
        }
        return SINGLETON;
    }

    // Connects to the FantApi.
    public FantApi getApi() {
        return retrofit.create(FantApi.class);
    }
}
