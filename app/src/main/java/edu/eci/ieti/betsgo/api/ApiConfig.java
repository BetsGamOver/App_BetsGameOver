package edu.eci.ieti.betsgo.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import edu.eci.ieti.betsgo.service.UserService;
import edu.eci.ieti.betsgo.utils.DateSerializer;
import edu.eci.ieti.betsgo.utils.TimeSerializer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static final String baseUrlE = "http://10.0.2.2:9090";
    private static Retrofit retrofit;
    private static String token = "";

    private static UserService service;

    static {
        initClient();
    }

    private static void initClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Time.class, new TimeSerializer())
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        StethoInterceptor stetho = new StethoInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(stetho);

        return builder.build();
    }

    public static void setToken(String value) {
        token = value;
        initClient();
    }

    public static UserService getUserService() {
        if (service == null) {
            service = retrofit.create(UserService.class);
        }
        return service;
    }
}