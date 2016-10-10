package com.samuelbostick.fitdeck;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    HomeFragment _hf;
    WorkoutFragment _wf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflateHomeFragment();
    }

    public void inflateHomeFragment(){
        if(_hf == null){
            _hf = HomeFragment.newInstance();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, _hf, "HomeFragment");
        ft.commit();
    }

    public void inflateWorkoutFragment(){
        if(_wf == null){
            String[] suites = {"Push Ups", "Sit Ups", "Squats", "Flutter Kicks"};
            _wf = WorkoutFragment.newInstance(suites);
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, _wf, "WorkoutFragment");
        ft.commit();
    }
}
