package com.example.remindme.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.remindme.app.home.HomeActivity;
import com.example.remindme.app.home.HomeViewModel;
import com.example.remindme.app.insert.InsertActivity;
import com.example.remindme.app.insert.InsertViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public ViewModelFactory(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(context instanceof HomeActivity)
            return (T) new HomeViewModel(context);
        else if (context instanceof InsertActivity)
            return (T) new InsertViewModel(context);
        return null;
    }
}
