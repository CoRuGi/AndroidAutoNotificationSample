package com.androidnanodegree.cr.androidautonotificationsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.androidnanodegree.cr.androidautonotificationsample.util.AndroidAutoNotification;

public class MyMessageHeardReceiver extends BroadcastReceiver {
    public MyMessageHeardReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // First we get the conversationId
        int conversationId = intent.getIntExtra(AndroidAutoNotification.CONVERSATION_ID, -1);

        // Write to log to show we know which conversation this Broadcast belongs to
        Log.d(
                "AndroidAutoNotification",
                "MyMessageHeardReceiver for conversation_id=" + conversationId
        );
    }
}
