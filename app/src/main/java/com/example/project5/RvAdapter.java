package com.example.project5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvViewHolder> {
    ArrayList<NotesModel> data;

    public RvAdapter(ArrayList<NotesModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.rv_layout,parent,false);
        RvViewHolder holder=new RvViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        holder.populate(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
