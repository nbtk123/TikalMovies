package com.example.nbtk123.tikalmovies.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.nbtk123.tikalmovies.App;

/**
 * Created by nbtk123 on 22/01/2017.
 */

public class MovieDataFethcer {
    private static final String BASE_URL = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=5adb14b7c13f3bc45f8dc26f650a1c7c";
    private static RequestQueue reqQueue = Volley.newRequestQueue(App.getAppContext());

    public static void fetchMoviesData() {
        String url = BASE_URL;
        MovieDataRequest req = new MovieDataRequest(url);
        reqQueue.add(req);
    }
}
