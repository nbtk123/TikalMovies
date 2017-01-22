package com.example.nbtk123.tikalmovies.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nbtk123.tikalmovies.R;
import com.example.nbtk123.tikalmovies.data.MovieData;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by nbtk123 on 22/01/2017.
 */

public class MasterFragment extends Fragment implements RVMoviesAdapter.OnItemClickListener {

    public static final String TAG = MasterFragment.class.getSimpleName();

    protected RecyclerView rvMovies;
    protected RVMoviesAdapter moviesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moviesAdapter = new RVMoviesAdapter(getActivity(), this);
        EventBus.getDefault().register(moviesAdapter);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(moviesAdapter);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_master, container, false);

        rvMovies = (RecyclerView) root.findViewById(R.id.rvMovies);
        initRecyclerView();

        return root;
    }

    private void initRecyclerView() {
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvMovies.setAdapter(moviesAdapter);
    }

    @Override
    public void onItemClicked(MovieData data) {
        DetailsFragment detailsFragment = new DetailsFragment();

        Bundle args = new Bundle();
        args.putParcelable(DetailsFragment.ARG_MOVIE_DATA, data);
        detailsFragment.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, detailsFragment, DetailsFragment.TAG).addToBackStack(null).commit();
    }
}
