package com.example.remindme.app.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.remindme.base.BaseAdapter;
import com.example.remindme.databinding.ListScheduleItemBinding;
import com.example.remindme.model.Schedule;

import java.util.List;

public class HomeAdapter extends BaseAdapter<ListScheduleItemBinding, Schedule> {

    private Context context;
    private List<Schedule> listSchedule;

    public HomeAdapter(Context context, List<Schedule> listSchedule, int layout){
        super(context, listSchedule, layout);
        this.context = context;
        this.listSchedule = listSchedule;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        ((ListScheduleItemBinding) viewHolder.binding).setViewModel(listSchedule.get(i));
    }
}
