package com.example.remindme.base;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

public class BaseAdapter<DataBinding extends ViewDataBinding, Model extends BaseObservable> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private Context context;
    private List<Model> listModel;
    private int layout;

    public BaseAdapter(Context context, List<Model> listModel, int layout){
        this.context = context;
        this.listModel = listModel;
        this.layout = layout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public DataBinding binding;
        public ViewHolder(DataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        DataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), layout, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }


}
