package com.example.nbtk123.tikalmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nbtk123 on 22/01/2017.
 */

public class MovieData implements Parcelable {

    private String title;
    private String imgUrl;
    private String overview;

    public MovieData() {}

    public MovieData(Parcel in) {
        title = in.readString();
        overview = in.readString();
        imgUrl = in.readString();
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getOverview() {
        return overview;
    }

    //PARCELABLE
    public static final Creator<MovieData> CREATOR = new Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel in) {
            return new MovieData(in);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(imgUrl);
    }
}
