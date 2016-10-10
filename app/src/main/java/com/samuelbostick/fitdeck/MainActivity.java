package com.samuelbostick.fitdeck;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    HomeFragment _hf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflateHomeFragment();
    }

    private void inflateHomeFragment(){
        if(_hf == null){
            _hf = HomeFragment.newInstance();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.activity_main, _hf, "Home_Fragment");
        ft.commit();
    }
}
