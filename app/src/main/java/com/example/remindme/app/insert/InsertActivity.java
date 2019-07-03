package com.example.remindme.app.insert;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.remindme.R;
import com.example.remindme.base.BaseActivity;
import com.example.remindme.databinding.InsertActivityBinding;
import com.example.remindme.model.Schedule;
import com.example.remindme.service.Notification;

import java.util.Calendar;

public class InsertActivity extends BaseActivity<InsertActivityBinding, InsertViewModel>
            implements View.OnClickListener{
    public InsertActivity(){
        super(InsertViewModel.class, R.layout.insert_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            // INSERT
            getBinding().txtDelete.setVisibility(View.GONE);
            getBinding().setViewModel(new Schedule().setTime("00:00"));
        }
        else{
            // UPDATE
            getBinding().txtDelete.setVisibility(View.VISIBLE);
            getBinding().setViewModel((Schedule) bundle.getSerializable("schedule"));
        }
    }

    @Override
    protected void setListener() {
        getBinding().back.setOnClickListener(this);
        getBinding().btnSave.setOnClickListener(this);
        getBinding().btnCancel.setOnClickListener(this);
        getBinding().txtDelete.setOnClickListener(this);
        getBinding().txtTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().back) || v.equals(getBinding().btnCancel)){
            finish();
        }
        else if (v.equals(getBinding().btnSave)){
            Schedule schedule = getBinding().getViewModel();
            if(getViewModel().validateInput(schedule)){
                boolean result = false;
                if(schedule.getId() == 0){
                    // INSERT
                    result = getViewModel().insertSchedule(schedule);
                }
                else{
                    // UPDATE
                    result = getViewModel().updateSchedule(schedule);
                }

                if(result){
                    getViewModel().setAlarm(schedule);
                    successToast();
                    finish();
                }
                else{
                    errorToast();
                }
            }
            else{
                Toast.makeText(this, "Please set your time and description", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.equals(getBinding().txtDelete)){
            AlertDialog.Builder dialog = createDialogConfirmation("Delete", "Are you sure want to delete this schedule?");
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(getViewModel().deleteSchedule(getBinding().getViewModel().getId())){
                        successToast();
                        finish();
                    }
                    else{
                        errorToast();
                    }
                }
            });
            dialog.setNegativeButton("No", null);
            dialog.show();
        }
        else if (v.equals(getBinding().txtTime)){
            try{
                String currTime = getBinding().txtTime.getText().toString();
                int hour = Integer.parseInt(currTime.substring(0,2));
                int minute = Integer.parseInt(currTime.substring(3,5));
                TimePickerDialog dialog = new TimePickerDialog(InsertActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = String.format("%02d", hourOfDay)+ ":" + String.format("%02d", minute);
                        getBinding().txtTime.setText(time);
                    }
                }, hour, minute, true);
                dialog.show();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
