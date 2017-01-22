package com.example.nbtk123.tikalmovies.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nbtk123.tikalmovies.R;
import com.example.nbtk123.tikalmovies.data.MovieData;
import com.squareup.picasso.Picasso;

/**
 * Created by nbtk123 on 22/01/2017.
 */

public class DetailsFragment extends Fragment {

    public static final String TAG = DetailsFragment.class.getSimpleName();
    public static final String ARG_MOVIE_DATA = "ARG_MOVIE_DATA";

    private TextView tvTitle;
    private TextView tvOverview;
    private ImageView imageView;

    private MovieData movieData;

    public void setMovieData(MovieData movieData) {
        this.movieData = movieData;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            movieData = getArguments().getParcelable(ARG_MOVIE_DATA);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ARG_MOVIE_DATA, movieData);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            movieData = savedInstanceState.getParcelable(ARG_MOVIE_DATA);
            bindMovieData();
        }
        super.onViewStateRestored(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_deatils, container, false);

        tvTitle = (TextView) root.findViewById(R.id.tvTitle);
        tvOverview = (TextView) root.findViewById(R.id.tvOverview);
        imageView = (ImageView) root.findViewById(R.id.imageView);

        bindMovieData();

        return root;
    }

    public void bindMovieData() {
        if (movieData != null) {
            tvTitle.setText(movieData.getTitle());
            tvOverview.setText(movieData.getOverview());
            Picasso.with(getActivity()).load(movieData.getImgUrl()).into(imageView);
        }
    }
}
