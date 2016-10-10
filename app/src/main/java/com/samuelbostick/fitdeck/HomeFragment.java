package com.samuelbostick.fitdeck;

import android.os.Bundle;
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

        return v;
    }

    class OnStartClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MainActivity activity = (MainActivity)getActivity();
            activity.inflateWorkoutFragment();
        }
    }
}
