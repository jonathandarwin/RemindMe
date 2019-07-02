package com.example.remindme.service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.remindme.model.Schedule;
import com.example.remindme.repository.ScheduleRepository;

public class Notification extends BroadcastReceiver {

    private ScheduleRepository repo;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("masuksiniga", "masuk notif");
        Schedule schedule = (Schedule) intent.getSerializableExtra("schedule");
        repo = new ScheduleRepository(context);
        schedule = repo.getScheduleById(schedule.getId());
        if(schedule.getId() != 0){
            // NOTIF
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder notif = new NotificationCompat.Builder(context, "PRIMARY")
                    .setContentTitle(schedule.getDescription());
            notificationManager.notify(schedule.getId(), notif.build());
        }
    }
}
