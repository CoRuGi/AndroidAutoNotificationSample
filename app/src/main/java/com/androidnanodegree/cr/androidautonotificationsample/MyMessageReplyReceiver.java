package com.androidnanodegree.cr.androidautonotificationsample;

import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androidnanodegree.cr.androidautonotificationsample.util.AndroidAutoNotification;

public class MyMessageReplyReceiver extends BroadcastReceiver {
    public MyMessageReplyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // First we get the conversationId
        int conversationId = intent.getIntExtra(AndroidAutoNotification.CONVERSATION_ID, -1);

        // Now we can retrieve the text of the reply and show it in the debbug logging
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (null != remoteInput) {
            CharSequence replyText = remoteInput.getCharSequence(AndroidAutoNotification.VOICE_REPLY_KEY);
            Log.d(
                    "AndroidAutoNotification",
                    "Found voice reply [" + replyText + "] from conversation with id " +
                            conversationId
            );
        }
    }
}
