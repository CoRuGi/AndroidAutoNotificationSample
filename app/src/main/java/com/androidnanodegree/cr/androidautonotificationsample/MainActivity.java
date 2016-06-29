package com.androidnanodegree.cr.androidautonotificationsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidnanodegree.cr.androidautonotificationsample.util.AndroidAutoNotification;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Launch the Android Auto Notification
     */
    public void createAndroidAutoNotification(View view) {
        AndroidAutoNotification androidAutoNotification = new AndroidAutoNotification(this);
        androidAutoNotification.show();
    }
}
