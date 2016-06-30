package com.androidnanodegree.cr.androidautonotificationsample.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;

import com.androidnanodegree.cr.androidautonotificationsample.R;

/**
 * Most of the code is from the Basic Notification Sample provided by the samples of SDK 23
 * Original code can be found at: https://github.com/googlesamples/android-BasicNotifications
 */

public class AndroidAutoNotification {
    /**
     * A numeric value that identifies the notification that we'll be sending.
     * This value needs to be unique within this app, but it doesn't need to be
     * unique system-wide.
     */
    public static final int NOTIFICATION_ID = 1;

    /**
     * A key to be used to extract out the reply from the speech recognition in our
     * BroadcastReceiver.
     */
    public static final String VOICE_REPLY_KEY = "voice_reply_key";

    /**
     * A string to hold the conversationId in an Intent
     */
    public static final String CONVERSATION_ID = "conversation_id";

    /**
     * A member variable to store the context given by the constructor of this class
     */
    private Context mContext;

    public AndroidAutoNotification(Context context) {
        mContext = context;
    }

    public void show() {
        /**
         * Create an intent that will be fired when the user clicks the notification.
         * The intent needs to be packaged into a {@link android.app.PendingIntent} so that the
         * notification service can fire it on our behalf.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(mContext.getString(R.string.notification_url)));
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        /**
         * Here we specify a conversation ID. Normally this will be some kind of index from a data
         * structure
         */
        int conversationId = 13;

        /**
         * Create an MessageHeardIntent to be able to tell the MessageHeardReceiver that the message
         * has been heard by the user.
         * We add a flag to trigger the packages that have stopped. Our messages app might not be
         * running by the time it is heard by the driver.
         * We use setAction to trigger the BroadcastReceiver.
         */
        Intent messageHeardIntent = new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction("com.androidnanodegree.cr.androidautonotificationsample.MY_ACTION_MESSAGE_HEARD")
                .putExtra(CONVERSATION_ID, conversationId);

        /**
         * Here we create the PendingIntent as a wrapper for the MessageHeardIntent. We use the
         * conversationID, we pass the Intent and we set a flag to allow our notification to be
         * updated.
         */
        PendingIntent messageHeardPendingIntent =
                PendingIntent.getBroadcast(
                        mContext,
                        conversationId,
                        messageHeardIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        /**
         * Here we create a messageReplyIntent. The code is similar to the messageHeardIntent,
         * except for the action to trigger the BroadcastReceiver.
         */
        Intent messageReplyIntent = new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction("com.androidnanodegree.cr.androidautonotificationsample.MY_ACTION_MESSAGE_REPLY")
                .putExtra(CONVERSATION_ID, conversationId);

        PendingIntent messageReplyPendingIntent =
                PendingIntent.getBroadcast(
                        mContext,
                        conversationId,
                        messageReplyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        /**
         * Here we add the RemoteInput Object which will handle the speech recognition and deliver
         * it to our BroadcastReceiver as a string.
         */
        RemoteInput remoteInput = new RemoteInput.Builder(VOICE_REPLY_KEY)
                .setLabel(mContext.getString(R.string.speak_message))
                .build();

        /**
         * Now we create a dummy message that is from a particular sender. Here the name is
         * hardcoded, but normally you get it from the messages in your application.
         */
        String conversationName = "Coen Rundberg";
        NotificationCompat.CarExtender.UnreadConversation.Builder unreadConversationBuilder =
                new NotificationCompat.CarExtender.UnreadConversation.Builder(conversationName)
                        .setReadPendingIntent(messageHeardPendingIntent)
                        .setReplyAction(messageReplyPendingIntent, remoteInput);

        unreadConversationBuilder.addMessage("Hello there, how are you?")
                .setLatestTimestamp(System.currentTimeMillis());

        /**
         * Use NotificationCompat.Builder to set up our notification.
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);

        /**
         * Set the icon that will appear in the notification bar. This icon also appears
         * in the lower right hand corner of the notification itself.
         *
         * Important note: although you can use any drawable as the small icon, Android
         * design guidelines state that the icon should be simple and monochrome. Full-color
         * bitmaps or busy images don't render well on smaller screens and can end up
         * confusing the user.
         */
        builder.setSmallIcon(R.drawable.ic_stat_notification);

        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent);

        // Set the notification to auto-cancel. This means that the notification will disappear
        // after the user taps it, rather than remaining until it's explicitly dismissed.
        builder.setAutoCancel(true);

        /**
         * Build the notification's appearance.
         * Set the large icon, which appears on the left of the notification. In this
         * sample we'll set the large icon to be the same as our app icon. The app icon is a
         * reasonable default if you don't have anything more compelling to use as an icon.
         */
        builder.setLargeIcon(BitmapFactory.decodeResource(
                mContext.getResources(), R.mipmap.ic_launcher)
        );

        /**
         * Set the text of the notification. This sample sets the three most commonly used
         * text areas:
         * 1. The content title, which appears in large type at the top of the notification
         * 2. The content text, which appears in smaller text below the title
         * 3. The subtext, which appears under the text on newer devices. Devices running
         *    versions of Android prior to 4.2 will ignore this field, so don't use it for
         *    anything vital!
         */
        builder.setContentTitle(mContext.getString(R.string.android_auto_notification_title));
        builder.setContentText(mContext.getString(R.string.android_auto_notification_text));
        builder.setSubText(mContext.getString(R.string.android_auto_notification_subtext));

        /**
         * Send the notification. This will immediately display the notification icon in the
         * notification bar.
         * Use the NotificationManagerCompat for support on older versions
         */
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(mContext.getApplicationContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
