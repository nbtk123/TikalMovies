package com.example.nbtk123.tikalmovies.network;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nbtk123.tikalmovies.data.MovieData;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nbtk123 on 22/01/2017.
 */

public class MovieDataRequest extends JsonObjectRequest {

    private static String BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/";

    public MovieDataRequest(String url) {
        super(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<MovieData> data = new ArrayList<>();

                JSONArray items = response.optJSONArray("results");
                if (items != null) {
                    for (int i=0; i<items.length(); i++) {
                        JSONObject item = items.optJSONObject(i);
                        if (item != null) {
                            MovieData datum = new MovieData();
                            datum.setTitle(item.optString("title", "error"));
                            datum.setOverview(item.optString("overview", "error"));

                            String posterPath = item.optString("poster_path", "error");
                            if (posterPath != null) {
                                posterPath = posterPath.replace("\\/","");
                            }
                            datum.setImgUrl(BASE_IMG_URL + posterPath);
                            data.add(datum);
                        }
                    }
                }

                EventBus.getDefault().post(new EventMovieDataRequestSuccess(data));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                EventBus.getDefault().post(new EventMovieDataRequestError(error));
            }
        });
    }

    public static class EventMovieDataRequestSuccess {
        public ArrayList<MovieData> data;
        public EventMovieDataRequestSuccess(ArrayList<MovieData> data) {
            this.data = data;
        }
    }

    public static class EventMovieDataRequestError {
        public VolleyError err;
        public EventMovieDataRequestError(VolleyError err) {
            this.err = err;
        }
    }
}
