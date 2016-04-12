package com.group9.eda397.data.util;

import android.app.Application;

import com.group9.eda397.BuildConfig;

import java.io.File;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Utility class for HTTP calls and libraries
 *
 * @author palmithor
 * @since 24/03/16.
 */
public class HttpUtil {

    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB


    public static OkHttpClient.Builder createApiClient(final Application application, final Interceptor... interceptors) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(application.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().cache(cache);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        for (Interceptor i : interceptors) {
            builder.addInterceptor(i);
        }
        return builder;
    }

}
