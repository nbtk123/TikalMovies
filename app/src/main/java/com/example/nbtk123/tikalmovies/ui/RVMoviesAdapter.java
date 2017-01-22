package com.example.nbtk123.tikalmovies.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nbtk123.tikalmovies.R;
import com.example.nbtk123.tikalmovies.data.MovieData;
import com.example.nbtk123.tikalmovies.network.MovieDataRequest;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by nbtk123 on 22/01/2017.
 */

public class RVMoviesAdapter extends RecyclerView.Adapter<RVMoviesAdapter.RVHolder> {

    private static final String KEY_MOVIE_DATA = "KEY_MOVIE_DATA";

    private ArrayList<MovieData> mData;
    private WeakReference<Context> mContext;
    private OnItemClickListener externalClickListener;

    public interface OnItemClickListener {
        public void onItemClicked(MovieData data);
    }

    View.OnClickListener internalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MovieData data = (MovieData) v.getTag();
            externalClickListener.onItemClicked(data);
        }
    };

    public RVMoviesAdapter(Context context, OnItemClickListener listener) {
        mData = new ArrayList<>();
        mContext = new WeakReference<>(context);
        externalClickListener = listener;
    }

    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext.get()).inflate(R.layout.movie_rv_item, parent, false);
        itemView.setOnClickListener(internalClickListener);
        RVHolder holder = new RVHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RVHolder holder, int position) {
        MovieData movieData = mData.get(position);
        Picasso.with(mContext.get()).load(movieData.getImgUrl()).into(holder.imageView);

        //For click handling
        holder.itemView.setTag(movieData);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MovieDataRequest.EventMovieDataRequestSuccess event) {
        mData.addAll(event.data);
        notifyItemRangeInserted(mData.size(), event.data.size());
    }
    @Subscribe
    public void onEvent(MovieDataRequest.EventMovieDataRequestError event) {
        Log.d("VolleyError", event.toString());
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelableArrayList(KEY_MOVIE_DATA, mData);
        }
    }

    public void onViewStateRestored(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mData = savedInstanceState.getParcelableArrayList(KEY_MOVIE_DATA);
        }
    }

    public static class RVHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public RVHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.rv_item_img);
        }
    }
}
