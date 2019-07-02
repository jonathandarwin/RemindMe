package com.example.remindme.app.insert;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.remindme.repository.ScheduleRepository;

public class InsertViewModel extends ViewModel {

    private ScheduleRepository repo;

    public InsertViewModel(Context context){
        repo = new ScheduleRepository(context);
    }
}
