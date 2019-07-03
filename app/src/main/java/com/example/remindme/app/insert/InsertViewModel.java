package com.example.remindme.app.insert;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import com.example.remindme.model.Schedule;
import com.example.remindme.repository.ScheduleRepository;
import com.example.remindme.service.Notification;

import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;

public class InsertViewModel extends ViewModel {

    private ScheduleRepository repo;
    private Context context;

    public InsertViewModel(Context context){
        repo = new ScheduleRepository(context);
        this.context = context;
    }

    public boolean insertSchedule(Schedule schedule){
        long id = repo.insertSchedule(schedule);
        if(id != -1){
            schedule.setId((int) id);
            return true;
        }
        return false;
    }

    public boolean validateInput(Schedule schedule){
        if(schedule.getDescription() != null &&
           schedule.getDescription().length() > 0)
            return true;
        return false;
    }

    public boolean deleteSchedule(int id){
        return repo.deleteSchedule(id);
    }

    public boolean updateSchedule(Schedule schedule){
        return repo.updateSchedule(schedule);
    }

    public void setAlarm(Schedule schedule){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(schedule.getTime().substring(0,2)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(schedule.getTime().substring(3,5)));
        calendar.set(Calendar.SECOND, 0);
        long time = calendar.getTimeInMillis();
        if(System.currentTimeMillis() > time){
            time += (24 * 60 * 60 * 1000);
        }

        AlarmManager alarm = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, Notification.class);
        intent.putExtra("schedule", schedule);
        PendingIntent pending = PendingIntent.getBroadcast(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pending);
        createNotificationChannel();
    }

    private void createNotificationChannel()
    {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(
                    "PRIMARY",
                    "stand up notification",
                    NotificationManager.IMPORTANCE_HIGH
            );

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("description");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
