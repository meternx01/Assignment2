package com.example.assignment2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {


    private ResponsePojo dataSet;
    private CustomListener listener;

    public void setListener(CustomListener listener) {
        this.listener = listener;
    }

    public void setDataSet(ResponsePojo dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.onBindViewHolder(dataSet.results.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.results.size() : 0;
    }
}
