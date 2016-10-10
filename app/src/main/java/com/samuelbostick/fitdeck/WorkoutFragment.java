package com.samuelbostick.fitdeck;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

public class WorkoutFragment extends Fragment {
    public static WorkoutFragment newInstance(){
        WorkoutFragment fragment = new WorkoutFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout, container, false);

        ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500);
        animation.setDuration (5000);
        animation.setInterpolator (new DecelerateInterpolator());
        animation.start ();

        return v;
    }
}
