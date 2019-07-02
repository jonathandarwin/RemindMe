package com.example.remindme.app.insert;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.remindme.model.Schedule;
import com.example.remindme.repository.ScheduleRepository;

public class InsertViewModel extends ViewModel {

    private ScheduleRepository repo;

    public InsertViewModel(Context context){
        repo = new ScheduleRepository(context);
    }

    public boolean insertSchedule(Schedule schedule){
        return repo.insertSchedule(schedule);
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
}
