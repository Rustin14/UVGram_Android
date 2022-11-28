package com.example.uvgram.Connection;

import static com.example.uvgram.Connection.Constants.BASE_URL;

import com.example.uvgram.Interfaces.UVGramAPIService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UVGramAPIAdapter {

    private static UVGramAPIService API_SERVICE;

    public static UVGramAPIService getApiService() {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder httpClient =  new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            API_SERVICE = retrofit.create(UVGramAPIService.class);
        }

        return API_SERVICE;
    }


}
