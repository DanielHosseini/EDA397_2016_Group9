package com.group9.eda397.data;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group9.eda397.data.github.GitHubService;
import com.group9.eda397.data.util.HttpUtil;
import com.group9.eda397.data.util.Rfc339DateJsonAdapter;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Factory for providing instances of GitHub Service
 *
 * @author palmithor
 * @since 24/03/16.
 */
public class GitHubServiceFactory {

    public static String BASE_URL = "https://api.github.com";

    public static GitHubService getService(final Application app) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Rfc339DateJsonAdapter())
                .create();
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(HttpUtil.createApiClient(app).build()) // todo add interceptors if needed, access token for instance
                .baseUrl(BASE_URL)
                .build()
                .create(GitHubService.class);
    }
}