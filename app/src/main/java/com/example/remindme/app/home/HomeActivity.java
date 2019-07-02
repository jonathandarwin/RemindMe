package com.example.remindme.app.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.remindme.R;
import com.example.remindme.app.insert.InsertActivity;
import com.example.remindme.base.BaseActivity;
import com.example.remindme.databinding.HomeActivityBinding;
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
    }
}
