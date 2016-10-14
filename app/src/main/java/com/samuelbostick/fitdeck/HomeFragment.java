package com.samuelbostick.fitdeck;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Button startButton = (Button)v.findViewById(R.id.start_button);
        startButton.setOnClickListener(new OnStartClickListener());

        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.settings_button);
        fab.setOnClickListener(new OnSettingsClickListener());

        return v;
    }

    class OnSettingsClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            SettingsDialogFragment sdf = new SettingsDialogFragment();
            sdf.show(getFragmentManager(), MainActivity.TAG);
        }
    }

    class OnStartClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MainActivity activity = (MainActivity)getActivity();
            activity.inflateWorkoutFragment();
        }
    }
}
