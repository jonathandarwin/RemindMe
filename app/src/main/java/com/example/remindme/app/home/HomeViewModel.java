package com.example.remindme.app.home;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.remindme.model.Schedule;
import com.example.remindme.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    ScheduleRepository repo;

    public HomeViewModel(Context context){
        repo = new ScheduleRepository(context);
    }

    public List<Schedule> getListSchedule(){
        return repo.getListSchedule();
    }
}
