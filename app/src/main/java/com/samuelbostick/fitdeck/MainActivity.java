package com.samuelbostick.fitdeck;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "com.samuelbostick.fitdeck";

    private HomeFragment _hf;
    private WorkoutFragment _wf;
    private InterstitialAd _interstitialAd;
    private ArrayList<String> _suites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public String[] getSuites(){
        return _suites.toArray(new String[0]);
    }

    public void loadSuites(){
        _suites = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences(TAG, MODE_PRIVATE);
        String suites = "";
        suites = sp.getString("SUITES", suites);
        if(suites.equals("")){
            String[] s = {"Push-ups", "Squats", "Sit-ups", "Burpees"};
            _suites.addAll(Arrays.asList(s));
        }else{
            _suites.addAll(Arrays.asList(suites.split(",")));
        }
    }

    public void saveSuites(String[] suites){
        SharedPreferences sp = getSharedPreferences(TAG, MODE_PRIVATE);
        _suites = new ArrayList<>();
        _suites.addAll(Arrays.asList(suites));

        SharedPreferences.Editor e = sp.edit();
        e.putString("SUITES", TextUtils.join(",", suites));
        e.apply();
    }

    public void inflateHomeFragment() {
        if (_hf == null) {
            _hf = HomeFragment.newInstance();
        } else {
            if (_interstitialAd.isLoaded()) {
                _interstitialAd.show();
            }
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, _hf, "HomeFragment");
        ft.commit();
    }

    public void inflateWorkoutFragment(){
        loadSuites();
        _wf = WorkoutFragment.newInstance(_suites.toArray(new String[0]));

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, _wf, "WorkoutFragment");
        ft.commit();
    }
}
