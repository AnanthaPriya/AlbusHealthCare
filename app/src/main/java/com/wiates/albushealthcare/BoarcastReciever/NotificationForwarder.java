package com.wiates.albushealthcare.BoarcastReciever;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.wiates.albushealthcare.R;

public class NotificationForwarder extends BroadcastReceiver {

    NotificationCompat.Builder builder;
    NotificationManagerCompat notificationManager;

    private final String CHANNEL_ID = "99";

    @Override
    public void onReceive(Context context, Intent intent) {

        int id = intent.getIntExtra("ID",5);
        createNotificationChannel(context,intent);
        showLoggedInNotificatoin(id);

    }

    private void createNotificationChannel(Context context,Intent intent) {

        String doctor = intent.getStringExtra("DOCTOR_NAME");
        String phone = intent.getStringExtra("PHONE");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText("You Have an Appointment With Dr."+doctor)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager = NotificationManagerCompat.from(context);
    }

    private void showLoggedInNotificatoin(int id){
        notificationManager.notify(id, builder.build());
    }
}
