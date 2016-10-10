package com.samuelbostick.fitdeck;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    HomeFragment _hf;
    WorkoutFragment _wf;
    InterstitialAd _interstitialAd;
    boolean _isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _isFirst = true;

        MobileAds.initialize(getApplicationContext(), getString(R.string.ad_app_id));

        _interstitialAd = new InterstitialAd(this);
        _interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        _interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();


        inflateHomeFragment();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();

        _interstitialAd.loadAd(adRequest);
    }

    public void inflateHomeFragment(){
        if(_hf == null){
            _hf = HomeFragment.newInstance();
        }

        if(!_isFirst){
            if(_interstitialAd.isLoaded()){
                _interstitialAd.show();
            }
        }else{
            _isFirst = false;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, _hf, "HomeFragment");
        ft.commit();
    }

    public void inflateWorkoutFragment(){
        String[] suites = {"Push Ups", "Sit Ups", "Squats", "Flutter Kicks"};
        _wf = WorkoutFragment.newInstance(suites);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, _wf, "WorkoutFragment");
        ft.commit();
    }
}
