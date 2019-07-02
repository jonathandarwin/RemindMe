package com.example.remindme.app.insert;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.remindme.R;
import com.example.remindme.base.BaseActivity;
import com.example.remindme.databinding.InsertActivityBinding;

public class InsertActivity extends BaseActivity<InsertActivityBinding, InsertViewModel>
            implements View.OnClickListener{
    public InsertActivity(){
        super(InsertViewModel.class, R.layout.insert_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setListener() {
        getBinding().back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().back)){
            finish();
        }
    }
}
