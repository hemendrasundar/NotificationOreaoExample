package com.hemendra.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    private Context mcontext;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder mbuilder;
    public static final String NOTIFICATION_CHANNEL_ID="10001";

    NotificationHelper(Context context)
    {
        this.mcontext = context;
    }

    public void createNotification(String title,String message)
    {
        Intent resultIntent= new Intent(mcontext,MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPeningIntent = PendingIntent.getActivity(mcontext,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        mbuilder = new NotificationCompat.Builder(mcontext);
        mbuilder.setSmallIcon(R.mipmap.ic_launcher);
        mbuilder.setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(false)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPeningIntent);
        notificationManager =(NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel =new NotificationChannel(NOTIFICATION_CHANNEL_ID,"NOtification_Channel_Name",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            mbuilder.setChannelId(NOTIFICATION_CHANNEL_ID);

            notificationManager.createNotificationChannel(notificationChannel);

        }

        notificationManager.notify(0,mbuilder.build());

    }
}
