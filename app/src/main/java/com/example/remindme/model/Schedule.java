package com.example.remindme.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.remindme.BR;

import java.io.Serializable;

public class Schedule extends BaseObservable implements Serializable {
    protected int id;
    protected String time;
    protected String description;
    protected int isOn;

    @Bindable
    public String getTime() {
        return time;
    }

    public Schedule setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
        return this;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public Schedule setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
        return this;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public Schedule setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
        return this;
    }

    @Bindable
    public int getIsOn() {
        return isOn;
    }

    public Schedule setIsOn(int isOn) {
        this.isOn = isOn;
        notifyPropertyChanged(BR.isOn);
        return this;
    }
}
