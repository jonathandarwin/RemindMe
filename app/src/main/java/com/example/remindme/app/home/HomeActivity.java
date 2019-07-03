package com.example.remindme.app.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.remindme.R;
import com.example.remindme.app.insert.InsertActivity;
import com.example.remindme.base.BaseActivity;
import com.example.remindme.base.BaseAdapter;
import com.example.remindme.databinding.HomeActivityBinding;
import com.example.remindme.databinding.ListScheduleItemBinding;
import com.example.remindme.model.Schedule;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<HomeActivityBinding, HomeViewModel>
                implements View.OnClickListener{

    private List<Schedule> listSchedule;
    private HomeAdapter adapter;

    public HomeActivity(){
        super(HomeViewModel.class, R.layout.home_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemTouchHelper.SimpleCallback itemTouch = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Schedule schedule = ((ListScheduleItemBinding)((BaseAdapter.ViewHolder) viewHolder).binding).getViewModel();
                getViewModel().deleteSchedule(schedule.getId());
                listSchedule.remove(schedule);
                adapter.notifyItemRemoved(i);
                adapter.notifyDataSetChanged();
            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(getBinding().recyclerView);
    }

    @Override
    protected void setListener() {
        getBinding().fabAdd.setOnClickListener(this);
    }

    @Override
    protected void setAdapter() {
        listSchedule = new ArrayList<>();
        adapter = new HomeAdapter(this, listSchedule, R.layout.list_schedule_item, new onScheduleClick() {
            @Override
            public void onClick(Schedule schedule) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("schedule", schedule);
                gotoIntent(InsertActivity.class, bundle, false);
            }

            @Override
            public void onChange(Schedule schedule) {
                getViewModel().updateSchedule(schedule);
            }
        });
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().fabAdd)){
            gotoIntent(InsertActivity.class, null, false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListSchedule();
    }

    private void getListSchedule(){
        getBinding().noData.setVisibility(View.GONE);
        listSchedule.clear();
        listSchedule.addAll(getViewModel().getListSchedule());
        if(listSchedule.size() == 0){
            getBinding().noData.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    public interface onScheduleClick{
        void onClick(Schedule schedule);
        void onChange(Schedule schedule);
    }
}
