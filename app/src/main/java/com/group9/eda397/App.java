package com.group9.eda397;

import android.app.Application;
import android.content.Context;

/**
 * @author palmithor
 * @since 24/03/16.
 */
public class App extends Application {


    public static App get(final Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
