package com.example.remindme.app.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.remindme.base.BaseAdapter;
import com.example.remindme.databinding.ListScheduleItemBinding;
import com.example.remindme.model.Schedule;

import java.util.List;

public class HomeAdapter extends BaseAdapter<ListScheduleItemBinding, Schedule> {

    private Context context;
    private List<Schedule> listSchedule;
    private HomeActivity.onScheduleClick listener;

    public HomeAdapter(Context context, List<Schedule> listSchedule, int layout, HomeActivity.onScheduleClick listener){
        super(context, listSchedule, layout);
        this.context = context;
        this.listSchedule = listSchedule;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder viewHolder, final int i) {
        super.onBindViewHolder(viewHolder, i);
        Log.d("masuksiniga", "description : " + listSchedule.get(i).getDescription() + " id : " + Integer.toString(listSchedule.get(i).getId()));
        ((ListScheduleItemBinding) viewHolder.binding).setViewModel(listSchedule.get(i));
        ((ListScheduleItemBinding) viewHolder.binding).llSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(listSchedule.get(i));
            }
        });
    }
}
