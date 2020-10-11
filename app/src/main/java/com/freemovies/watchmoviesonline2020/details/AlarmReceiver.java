package com.freemovies.watchmoviesonline2020.details;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {

    final static String NOTIFICATION_CHANNEL = "Notification Channel";
    private static final int PENDING_INTENT = 100;

    public AlarmReceiver() {

    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        getWeather(context);

    }

    private void getWeather(final Context context) {
        String title = "Latest Movie Trailers";
        createNotification(context,title,"Watch Movie Trailers of 2020");

    }

    public void createNotification(Context context, String title, String message){
        Notification.Builder notification;
        NotificationManager manager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL,NOTIFICATION_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Notification");
            manager.createNotificationChannel(channel);
            notification = new Notification.Builder(context,NOTIFICATION_CHANNEL);
        }else{
            notification = new Notification.Builder(context);
        }
        Intent intent = new Intent(context,Movies.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,PENDING_INTENT,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentTitle(title)
                .setContentText(message)
                .setStyle(new Notification.BigTextStyle().bigText(message))
                .setContentIntent(pendingIntent);
        assert manager != null;
        manager.notify(3,notification.build());
    }

}
