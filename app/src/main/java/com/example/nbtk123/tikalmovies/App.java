package com.example.nbtk123.tikalmovies;

import android.app.Application;
import android.content.Context;

/**
 * Created by nbtk123 on 22/01/2017.
 */

public class App extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
