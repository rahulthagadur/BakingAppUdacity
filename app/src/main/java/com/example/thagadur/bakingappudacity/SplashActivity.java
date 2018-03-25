package com.example.thagadur.bakingappudacity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.thagadur.bakingappudacity.utils.MyActivity.launch;

public class SplashActivity extends AppCompatActivity {


    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initObjects();

        final Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {
                } finally {
                    launch(mContext, HomeActivity.class);
                }
            }
        };
        thread.start();

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void initObjects() {
        mContext = this;
    }
}
