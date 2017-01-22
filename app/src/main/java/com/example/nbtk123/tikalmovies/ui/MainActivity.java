package com.example.nbtk123.tikalmovies.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nbtk123.tikalmovies.R;
import com.example.nbtk123.tikalmovies.network.MovieDataFethcer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        // Verify phone-mode
        if (findViewById(R.id.tablet_master_fragment_container) == null) {
            MasterFragment masterFragment = new MasterFragment();
            masterFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.fragment_container, masterFragment, MasterFragment.TAG).commit();
        } else {
            //Tablet Mode
            MasterFragment masterFragment = new MasterFragment();
            masterFragment.setArguments(getIntent().getExtras());

            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(R.id.tablet_master_fragment_container, masterFragment, MasterFragment.TAG)
                    .add(R.id.tablet_details_fragment_container, detailsFragment, DetailsFragment.TAG).commit();
        }

        MovieDataFethcer.fetchMoviesData();
    }
}
