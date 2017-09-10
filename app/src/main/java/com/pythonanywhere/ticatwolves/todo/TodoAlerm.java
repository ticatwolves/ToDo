package com.pythonanywhere.ticatwolves.todo;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class TodoAlerm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat myManager = NotificationManagerCompat.from(context);

        NotificationCompat.Builder mynoti = new NotificationCompat.Builder(context);
        mynoti.setContentTitle("ToDo Notification");
        mynoti.setContentText("Get up");
        mynoti.setSmallIcon(android.R.drawable.ic_btn_speak_now);

        Intent in = new Intent(context,MainActivity.class);
        PendingIntent pd = PendingIntent.getActivity(context,0,in,0);

        mynoti.setContentIntent(pd);

        mynoti.setAutoCancel(true);

        myManager.notify(1,mynoti.build());


    }
}
