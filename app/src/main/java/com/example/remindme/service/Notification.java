package com.example.remindme.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.remindme.R;
import com.example.remindme.app.home.HomeActivity;
import com.example.remindme.model.Schedule;
import com.example.remindme.repository.ScheduleRepository;

public class Notification extends BroadcastReceiver {

    private ScheduleRepository repo;

    @Override
    public void onReceive(Context context, Intent intent) {
        Schedule schedule = (Schedule) intent.getSerializableExtra("schedule");
        Log.d("masuksiniga", "id notif : " + Integer.toString(schedule.getId()));
        repo = new ScheduleRepository(context);
        schedule = repo.getScheduleById(schedule.getId());
        Log.d("masuksiniga","description schedule : " + schedule.getDescription());
        if(schedule.getId() != 0){
            // NOTIF
            Log.d("masuksiniga","notif yeay");
            Intent intentNotif = new Intent(context, HomeActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, schedule.getId(), intentNotif, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder notif = new NotificationCompat.Builder(context, "PRIMARY")
                    .setContentTitle("RemindMe")
                    .setSmallIcon(R.drawable.ic_add)
                    .setContentText(schedule.getDescription())
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL);
            notificationManager.notify(schedule.getId(), notif.build());
        }
    }
}
