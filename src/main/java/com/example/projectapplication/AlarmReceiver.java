package com.example.projectapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        showNotification(context, "wake up!!!");
    }

    private void showNotification(Context context, String message)
    {
        Intent intent = new Intent(context, AlarmActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setTicker(message)
                .setContentTitle("You havdhfjhdfgjhr")
                .setContentText(message)
                .addAction(R.mipmap.ic_launcher_round, "Time is up", pendingIntent)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationmanager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        notificationmanager.notify(0, builder.build());
    }
}
